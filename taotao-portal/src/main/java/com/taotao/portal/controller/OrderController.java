package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbOrder;
import com.taotao.portal.pojo.TaotaoResult;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;

	/**
	 * @author: tushengtao
	 * @date: 2019/12/10
	 * @Description:  前台使用 ajax post 请求 传递Json数据 Order order ,订单创建成功 跳转到支付页面
	 * @param:
	 * @return:
	 */
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	@ResponseBody
	public String createOrder(@RequestBody Order order) {
			String orderId = orderService.createOrder(order);
		    System.out.println(orderId);
			// 返回订单
			return orderId;
	}
	/**
	 * @author: tushengtao
	 * @date: 2019/12/18
	 * @Description:  根据用户id: userId 查询订单，前台传入 uid
	 * @param:
	 * @return:
	 */
	@RequestMapping(value = "/select",produces= MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String selectByUid(String userId){
		Long userIdLong=Long.parseLong(userId);
		TbOrder order=new TbOrder();
		order.setUserId(userIdLong);
		String result=orderService.selectOrderByUid(order);
		return result;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteById(TbOrder order){
		String result=orderService.deleteOrderById(order);
		return TaotaoResult.ok(result);
	}
	@RequestMapping("/updateOrderStatus")
	@ResponseBody
	public String updateOrderStatus(String orderId,int status){
		String result=orderService.updateOrderStatus(orderId,status);
		return result;
	}

}
