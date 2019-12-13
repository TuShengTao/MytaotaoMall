package com.taotao.portal.controller;

import com.taotao.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

/**
 * @author: tushengtao
 * @date: 2019/11/26
 * @Description:  商品详情页面的展示 传入商品id
 * 调取服务层 展示商品 ：基本信息、商品描述、商品规格参数（ajax）
 * @param:
 * @return:
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/item/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String showItemByAjax(@PathVariable Long itemId) {
		ItemInfo item = itemService.getItemById(itemId);
		//实际上是转换为string ,此注解@ResponseBody 再转为 ajax 要求返回的json
		String result= JsonUtils.objectToJson(item);
		return result;
	}
	
	@RequestMapping(value="/item/desc/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId) {
		String string = itemService.getItemDescById(itemId);
		//@ResponseBody 不起作用 解决方法：
		String result=JsonUtils.objectToJson(string);
		return result;
	}
	
	@RequestMapping(value="/item/param/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId) {
		String string = itemService.getItemParam(itemId);
		//@ResponseBody 不起作用 解决方法：
		String result=JsonUtils.objectToJson(string);
		return string;
	}
}
