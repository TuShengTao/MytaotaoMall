package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: tushengtao
 * @date: 2019/12/3
 * @Description: 购物车Controller
 * @param:
 * @return:
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping("/add/{itemId}")
	@ResponseBody
	public String addCartItem(@PathVariable Long itemId,
			@RequestParam(defaultValue="1")Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		// 使用 ajax 请求设置 返回cookie
		response.setHeader("Access-Control-Allow-Credentials", "true");
		TaotaoResult result = cartService.addCartItem(itemId, num, request, response);
		CookieUtils.getCookieValue(request,"TT_CART");
		String string="success";
		String resultSucess= JsonUtils.objectToJson(string);
		return resultSucess;
	}
	@RequestMapping("/success")
	public String showSuccess() {
		return "cartSuccess";
	}
	
	@RequestMapping("/myCart")
	public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "myCart";
	}

	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
		cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}

}
