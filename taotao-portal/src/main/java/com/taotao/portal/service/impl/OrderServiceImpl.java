package com.taotao.portal.service.impl;

import com.taotao.pojo.TbOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.OrderService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: tushengtao
 * @date: 2019/12/9
 * @Description:  订单处理Service
 * @param:
 * @return:
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;

	@Value("${ORDER_SELECTBYUID_URL}")
	private String ORDER_SELECTBYUID_URL;

	@Value("${ORDER_DELETEBYID_URL}")
	private String ORDER_DELETEBYID_URL;

	@Value("${ORDER_UPDATESTATUSBYID_URL}")
	private String ORDER_UPDATESTATUSBYID_URL;

	@Override
	public String createOrder(Order order) {
		//调用创建订单服务之前补全用户信息。
		//从cookie中后取TT_TOKEN的内容，根据token调用sso系统的服务根据token换取用户信息。
		
		//调用taotao-order的服务提交订单。
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		//把json转换成taotaoResult
		TaotaoResult taotaoResult = TaotaoResult.format(json);
		if (taotaoResult.getStatus() == 200) {
			Object orderId = taotaoResult.getData();
			return orderId.toString();
		}
		return "";
	}

	@Override
	public String selectOrderByUid(TbOrder order) {
		//调用taotao-order的服务提交订单。
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL +ORDER_SELECTBYUID_URL, JsonUtils.objectToJson(order));

		return json;
	}

	@Override
	public String deleteOrderById(TbOrder order) {
		String json=HttpClientUtil.doGet(ORDER_BASE_URL+ORDER_DELETEBYID_URL+"?orderId="+order.getOrderId());
		return json;
	}

	@Override
	public String updateOrderStatus(String orderId, int status) {
		String json=HttpClientUtil.doGet(ORDER_BASE_URL+ORDER_UPDATESTATUSBYID_URL+"?orderId="+orderId+"&status="+status);
		return json;
	}
}