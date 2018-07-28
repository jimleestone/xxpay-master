package org.xxpay.service.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: dingzhiwei
 * @date: 17/10/26
 * @description: 转账业务
 */
public interface TransOrderService {

	int create(JSONObject transOrderObj);

	Map select(String jsonParam);

	Map selectByMchIdAndTransOrderId(String jsonParam);

	Map selectByMchIdAndMchTransNo(String jsonParam);

	Map updateStatus4Ing(String jsonParam);

	Map updateStatus4Success(String jsonParam);

	Map updateStatus4Complete(String jsonParam);

	int sendTransNotify(String transOrderId, String channelName);

}
