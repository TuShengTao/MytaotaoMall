package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.taotao.common.pojo.EUDataGridResult;
//import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 商品管理Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p>
 * @author tushengtao
 * @version 1.0
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")//地址栏url路径映射到此controller方法
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	@RequestMapping(value = "/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemDesc(itemId);
		return result;
	}
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	private TaotaoResult createItem(TbItem item, String desc, String itemParams) throws Exception {
	    /**
	     * @author: tushengtao
	     * @date: 2019/10/24
	     * @Description: 使用pojo来接收新增商品数据 表单名字要和属性值一致 返回TaotaoResult
	     * @param: [item, desc, itemParams]
	     * @return: com.taotao.common.pojo.TaotaoResult
	     */
		TaotaoResult result = itemService.createItem(item,desc);
		return result;
	}
	@RequestMapping(value = "/item/delete",method = RequestMethod.POST)
	@ResponseBody
	private String deleteById(Long ids){
		int flag=itemService.deleteByPrimaryKey(ids);
		// 返回 为1
		return String.valueOf(flag);
	}

}
