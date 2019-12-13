package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.utils.JsonUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
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
			// 测试
			System.out.println(order.getOrderItems());
//			//从Request中取用户信息
			//TbUser user = (TbUser) request.getAttribute("user");
//			//在order对象中补全用户信息
		//	order.setUserId();
//			order.setBuyerNick(user.getUsername());
		  System.out.println("111111111111111111111111111111111111111111111111");
		  System.out.println(order);
			//调用服务
			String orderId = orderService.createOrder(order);
		    System.out.println(orderId);
			// 返回订单
			return orderId;
	}
}
