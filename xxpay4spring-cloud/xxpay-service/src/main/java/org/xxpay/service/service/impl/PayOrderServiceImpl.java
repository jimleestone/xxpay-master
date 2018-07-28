package org.xxpay.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.xxpay.common.constant.PayConstant;
import org.xxpay.dal.dao.mapper.PayOrderMapper;
import org.xxpay.dal.dao.model.PayOrder;
import org.xxpay.dal.dao.model.PayOrderExample;
import org.xxpay.service.service.PayOrderService;

/**
 * @Description:
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
@Service
public class PayOrderServiceImpl implements PayOrderService {

	@Autowired
	private PayOrderMapper payOrderMapper;

	@Override
	public int createPayOrder(PayOrder payOrder) {
		return payOrderMapper.insertSelective(payOrder);
	}

	@Override
	public PayOrder selectPayOrder(String payOrderId) {
		return payOrderMapper.selectByPrimaryKey(payOrderId);
	}

	@Override
	public PayOrder selectPayOrderByMchIdAndPayOrderId(String mchId, String payOrderId) {
		PayOrderExample example = new PayOrderExample();
		PayOrderExample.Criteria criteria = example.createCriteria();
		criteria.andMchIdEqualTo(mchId);
		criteria.andPayOrderIdEqualTo(payOrderId);
		List<PayOrder> payOrderList = payOrderMapper.selectByExample(example);
		return CollectionUtils.isEmpty(payOrderList) ? null : payOrderList.get(0);
	}

	@Override
	public PayOrder selectPayOrderByMchIdAndMchOrderNo(String mchId, String mchOrderNo) {
		PayOrderExample example = new PayOrderExample();
		PayOrderExample.Criteria criteria = example.createCriteria();
		criteria.andMchIdEqualTo(mchId);
		criteria.andMchOrderNoEqualTo(mchOrderNo);
		List<PayOrder> payOrderList = payOrderMapper.selectByExample(example);
		return CollectionUtils.isEmpty(payOrderList) ? null : payOrderList.get(0);
	}

	@Override
	public int updateStatus4Ing(String payOrderId, String channelOrderNo) {
		PayOrder payOrder = new PayOrder();
		payOrder.setStatus(PayConstant.PAY_STATUS_PAYING);
		if (channelOrderNo != null)
			payOrder.setChannelOrderNo(channelOrderNo);
		payOrder.setPaySuccTime(System.currentTimeMillis());
		PayOrderExample example = new PayOrderExample();
		PayOrderExample.Criteria criteria = example.createCriteria();
		criteria.andPayOrderIdEqualTo(payOrderId);
		criteria.andStatusEqualTo(PayConstant.PAY_STATUS_INIT);
		return payOrderMapper.updateByExampleSelective(payOrder, example);
	}

	@Override
	public int updateStatus4Success(String payOrderId) {
		PayOrder payOrder = new PayOrder();
		payOrder.setPayOrderId(payOrderId);
		payOrder.setStatus(PayConstant.PAY_STATUS_SUCCESS);
		payOrder.setPaySuccTime(System.currentTimeMillis());
		PayOrderExample example = new PayOrderExample();
		PayOrderExample.Criteria criteria = example.createCriteria();
		criteria.andPayOrderIdEqualTo(payOrderId);
		criteria.andStatusEqualTo(PayConstant.PAY_STATUS_PAYING);
		return payOrderMapper.updateByExampleSelective(payOrder, example);
	}

	@Override
	public int updateStatus4Complete(String payOrderId) {
		PayOrder payOrder = new PayOrder();
		payOrder.setPayOrderId(payOrderId);
		payOrder.setStatus(PayConstant.PAY_STATUS_COMPLETE);
		PayOrderExample example = new PayOrderExample();
		PayOrderExample.Criteria criteria = example.createCriteria();
		criteria.andPayOrderIdEqualTo(payOrderId);
		criteria.andStatusEqualTo(PayConstant.PAY_STATUS_SUCCESS);
		return payOrderMapper.updateByExampleSelective(payOrder, example);
	}

	@Override
	public int updateNotify(String payOrderId, byte count) {
		PayOrder newPayOrder = new PayOrder();
		// TODO 并发下次数问题待解决
		newPayOrder.setNotifyCount(count);
		newPayOrder.setLastNotifyTime(System.currentTimeMillis());
		newPayOrder.setPayOrderId(payOrderId);
		return payOrderMapper.updateByPrimaryKeySelective(newPayOrder);
	}

	@Override
	public int updateNotify(PayOrder payOrder) {
		return payOrderMapper.updateByPrimaryKeySelective(payOrder);
	}

}
