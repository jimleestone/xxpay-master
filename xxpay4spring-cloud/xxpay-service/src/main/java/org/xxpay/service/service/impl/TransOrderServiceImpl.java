package org.xxpay.service.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xxpay.common.domain.BaseParam;
import org.xxpay.common.enumm.RetEnum;
import org.xxpay.common.util.BeanConvertUtils;
import org.xxpay.common.util.JsonUtil;
import org.xxpay.common.util.MyLog;
import org.xxpay.common.util.ObjectValidUtil;
import org.xxpay.common.util.RpcUtil;
import org.xxpay.dal.dao.model.TransOrder;
import org.xxpay.service.mq.Mq4TransNotify;
import org.xxpay.service.service.TransOrderService;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: dingzhiwei
 * @date: 17/10/30
 * @description:
 */
@Service
public class TransOrderServiceImpl extends BaseService4TransOrder implements TransOrderService {

	private static final MyLog _log = MyLog.getLog(TransOrderServiceImpl.class);

	@Autowired
	private Mq4TransNotify mq4TransNotify;

	@Override
	public int create(JSONObject transOrderObj) {
		TransOrder transOrder = BeanConvertUtils.map2Bean(transOrderObj, TransOrder.class);
		int result = super.baseCreateTransOrder(transOrder);
		return result;
	}

	@Override
	public Map select(String jsonParam) {
		BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
		Map<String, Object> bizParamMap = baseParam.getBizParamMap();
		if (ObjectValidUtil.isInvalid(bizParamMap)) {
			_log.warn("根据转账订单号查询转账订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
		}
		String transOrderId = baseParam.isNullValue("transOrderId") ? null : bizParamMap.get("transOrderId").toString();
		if (ObjectValidUtil.isInvalid(transOrderId)) {
			_log.warn("根据转账订单号查询转账订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
		}
		TransOrder transOrder = super.baseSelectTransOrder(transOrderId);
		if (transOrder == null)
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_DATA_NOT_EXISTS);
		String jsonResult = JsonUtil.object2Json(transOrder);
		return RpcUtil.createBizResult(baseParam, jsonResult);
	}

	@Override
	public Map selectByMchIdAndTransOrderId(String jsonParam) {
		BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
		Map<String, Object> bizParamMap = baseParam.getBizParamMap();
		if (ObjectValidUtil.isInvalid(bizParamMap)) {
			_log.warn("根据商户号和转账订单号查询转账订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
		}
		String mchId = baseParam.isNullValue("mchId") ? null : bizParamMap.get("mchId").toString();
		String transOrderId = baseParam.isNullValue("transOrderId") ? null : bizParamMap.get("transOrderId").toString();
		if (ObjectValidUtil.isInvalid(mchId, transOrderId)) {
			_log.warn("根据商户号和转账订单号查询转账订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
		}
		TransOrder transOrder = super.baseSelectByMchIdAndTransOrderId(mchId, transOrderId);
		if (transOrder == null)
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_DATA_NOT_EXISTS);
		String jsonResult = JsonUtil.object2Json(transOrder);
		return RpcUtil.createBizResult(baseParam, jsonResult);
	}

	@Override
	public Map selectByMchIdAndMchTransNo(String jsonParam) {
		BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
		Map<String, Object> bizParamMap = baseParam.getBizParamMap();
		if (ObjectValidUtil.isInvalid(bizParamMap)) {
			_log.warn("根据商户号和商户订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
		}
		String mchId = baseParam.isNullValue("mchId") ? null : bizParamMap.get("mchId").toString();
		String mchTransNo = baseParam.isNullValue("mchTransNo") ? null : bizParamMap.get("mchTransNo").toString();
		if (ObjectValidUtil.isInvalid(mchId, mchTransNo)) {
			_log.warn("根据商户号和商户订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
		}
		TransOrder transOrder = super.baseSelectByMchIdAndMchTransNo(mchId, mchTransNo);
		if (transOrder == null)
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_DATA_NOT_EXISTS);
		String jsonResult = JsonUtil.object2Json(transOrder);
		return RpcUtil.createBizResult(baseParam, jsonResult);
	}

	@Override
	public Map updateStatus4Ing(String jsonParam) {
		BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
		Map<String, Object> bizParamMap = baseParam.getBizParamMap();
		if (ObjectValidUtil.isInvalid(bizParamMap)) {
			_log.warn("修改转账订单状态失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
		}
		String transOrderId = baseParam.isNullValue("transOrderId") ? null : bizParamMap.get("transOrderId").toString();
		String channelOrderNo = baseParam.isNullValue("channelOrderNo") ? null
				: bizParamMap.get("channelOrderNo").toString();
		if (ObjectValidUtil.isInvalid(transOrderId)) {
			_log.warn("修改转账订单状态失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
		}
		int result = super.baseUpdateStatus4Ing(transOrderId, channelOrderNo);
		return RpcUtil.createBizResult(baseParam, result);
	}

	@Override
	public Map updateStatus4Success(String jsonParam) {
		BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
		Map<String, Object> bizParamMap = baseParam.getBizParamMap();
		if (ObjectValidUtil.isInvalid(bizParamMap)) {
			_log.warn("修改转账订单状态失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
		}
		String transOrderId = baseParam.isNullValue("transOrderId") ? null : bizParamMap.get("transOrderId").toString();
		if (ObjectValidUtil.isInvalid(transOrderId)) {
			_log.warn("修改转账订单状态失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
		}
		int result = super.baseUpdateStatus4Success(transOrderId);
		return RpcUtil.createBizResult(baseParam, result);
	}

	@Override
	public Map updateStatus4Complete(String jsonParam) {
		BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
		Map<String, Object> bizParamMap = baseParam.getBizParamMap();
		if (ObjectValidUtil.isInvalid(bizParamMap)) {
			_log.warn("修改转账订单状态失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
		}
		String transOrderId = baseParam.isNullValue("transOrderId") ? null : bizParamMap.get("transOrderId").toString();
		if (ObjectValidUtil.isInvalid(transOrderId)) {
			_log.warn("修改转账订单状态失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
			return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
		}
		int result = super.baseUpdateStatus4Complete(transOrderId);
		return RpcUtil.createBizResult(baseParam, result);
	}

	@Override
	public int sendTransNotify(String transOrderId, String channelName) {
		JSONObject object = new JSONObject();
		object.put("transOrderId", transOrderId);
		object.put("channelName", channelName);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("msg", object);
		int result = 1;
		try {
			mq4TransNotify.send(paramMap.toString());
		} catch (Exception e) {
			_log.error(e, "");
			result = 0;
		}
		return result;
	}
}
