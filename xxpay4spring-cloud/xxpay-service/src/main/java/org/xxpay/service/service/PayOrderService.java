package org.xxpay.service.service;

import org.xxpay.dal.dao.model.PayOrder;

/**
 * @Description:
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
public interface PayOrderService {

	int updateNotify(PayOrder payOrder);

	int updateNotify(String payOrderId, byte count);

	int updateStatus4Complete(String payOrderId);

	int updateStatus4Success(String payOrderId);

	int updateStatus4Ing(String payOrderId, String channelOrderNo);

	PayOrder selectPayOrderByMchIdAndMchOrderNo(String mchId, String mchOrderNo);

	PayOrder selectPayOrderByMchIdAndPayOrderId(String mchId, String payOrderId);

	PayOrder selectPayOrder(String payOrderId);

	int createPayOrder(PayOrder payOrder);

}
