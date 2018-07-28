package org.xxpay.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xxpay.dal.dao.mapper.MchInfoMapper;
import org.xxpay.dal.dao.model.MchInfo;
import org.xxpay.service.service.MchInfoService;

/**
 * @Description:
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
@Service
public class MchInfoServiceImpl implements MchInfoService {

	@Autowired
	private MchInfoMapper mchInfoMapper;

	@Override
	public MchInfo selectMchInfo(String mchId) {
		return mchInfoMapper.selectByPrimaryKey(mchId);
	}

}
