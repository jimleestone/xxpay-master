package org.xxpay.service.service;

import java.util.Map;

/**
 * @author: dingzhiwei
 * @date: 17/9/9
 * @description:
 */
public interface PayChannel4WxService {

	Map doWxPayReq(String jsonParam);

	Map doWxTransReq(String jsonParam);

	Map getWxTransReq(String jsonParam);

	Map doWxRefundReq(String jsonParam);

	Map getWxRefundReq(String jsonParam);

}
