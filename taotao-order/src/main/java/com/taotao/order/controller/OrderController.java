package com.taotao.order.controller;

import com.taotao.pojo.Order;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.order.service.OrderService;

import java.util.List;

/**
 * @author: tushengtao
 * @date: 2019/12/18
 * @Description:
 * @param:
 * @return:
 */
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createOrder(@RequestBody Order order) {
		try {
			TaotaoResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			// 成功结果 包含orderid
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	@RequestMapping(value = "/select")
	@ResponseBody
	public TaotaoResult selectOrderByUid(@RequestBody TbOrder order){
		List<Order> orderList=orderService.selectOrderByUid(order);
		System.out.println(orderList);
		return TaotaoResult.ok(orderList);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteById(TbOrder order){

		int flag1=orderService.deleteOrderShipById(order);
		int flag3=orderService.deleteOrderById(order);
		if (flag1==1 && flag1==flag3){
			return TaotaoResult.ok();
		}else {
			return TaotaoResult.build(5,"删除失败！");
		}
	}
	@RequestMapping("/updateOrderStatus")
	@ResponseBody
	public TaotaoResult updateOrderStatus(String orderId,int status){
		// 支付宝支付成功调用此接口
		TbOrder order=new TbOrder();
		order.setStatus(status);
		order.setOrderId(orderId);
		int flag=orderService.updateOrderStatusById(order);
		if (flag==1){
			return TaotaoResult.ok();
		}else {
			return TaotaoResult.build(500,"更新失败！");
		}
	}
}
