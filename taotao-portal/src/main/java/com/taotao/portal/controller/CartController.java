package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

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
		// 使用 ajax 请求设置
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin","http://localhost:8082");
		TaotaoResult result = cartService.addCartItem(itemId, num, request, response);
		String string="success";
		String resultSucess= JsonUtils.objectToJson(string);
		return resultSucess;
	}
	@RequestMapping("/success")
	public String showSuccess() {
		return "cartSuccess";
	}

	//传入购物车商品里的id

	@RequestMapping(value = "/getCartItemInfo",method= RequestMethod.POST, produces= MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getCartItemInfo(String[] goodsId){
		Long arrayId[] = new Long[goodsId.length];
		for(int i=0;i<goodsId.length;i++) {
			arrayId[i] = Long.parseLong(goodsId[i]);
		}
		List<CartItem> cartItemList=cartService.getCartItemInfo(arrayId);
		String result=JsonUtils.objectToJson(cartItemList);
		return result;
	}

}
