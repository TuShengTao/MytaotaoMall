package com.taotao.portal.service;

import com.taotao.pojo.TbOrder;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.pojo.TaotaoResult;

public interface OrderService {

	String createOrder(Order order);
	String selectOrderByUid(TbOrder order);
	String deleteOrderById(TbOrder order);
	String updateOrderStatus(String orderId,int status);

}
