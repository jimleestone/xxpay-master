package org.xxpay.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.xxpay.dal.dao.mapper.PayChannelMapper;
import org.xxpay.dal.dao.model.PayChannel;
import org.xxpay.dal.dao.model.PayChannelExample;
import org.xxpay.service.service.PayChannelService;

/**
 * @Description:
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
@Service
public class PayChannelServiceImpl implements PayChannelService {

	@Autowired
	private PayChannelMapper payChannelMapper;

	@Override
	public PayChannel selectPayChannel(String channelId, String mchId) {
		PayChannelExample example = new PayChannelExample();
		PayChannelExample.Criteria criteria = example.createCriteria();
		criteria.andChannelIdEqualTo(channelId);
		criteria.andMchIdEqualTo(mchId);
		List<PayChannel> payChannelList = payChannelMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(payChannelList))
			return null;
		return payChannelList.get(0);
	}

}
