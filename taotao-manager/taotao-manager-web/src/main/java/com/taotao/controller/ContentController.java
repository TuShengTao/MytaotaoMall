package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

/**
 * @author: tushengtao
 * @date: 2019/11/2
 * @Description: 内容管理 Controller
 * @param:
 * @return:
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		// TbContent content 字段 要与表单域 input的name一致才能接收到值
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}
/**
 * @author: tushengtao
 * @date: 2019/11/3
 * @Description: content.jsp 右侧展示内容列表 并且分页
 * @param:
 * @return:
 */
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult selectContentById(Integer page,Integer rows,Long categoryId){
		EUDataGridResult result=contentService.selectContentById(page,rows,categoryId);
		return result;
	}

	//内容管理模块 修改、删除、 未完成


}
