package org.xxpay.service.service;

import org.xxpay.dal.dao.model.MchInfo;

/**
 * @Description:
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.xxpay.org
 */

public interface MchInfoService {

	MchInfo selectMchInfo(String mchId);

}
