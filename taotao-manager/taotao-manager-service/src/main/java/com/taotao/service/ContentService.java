package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	TaotaoResult insertContent(TbContent content);
	int deleteById(Long id);
	int updateById(TbContent content);
	EUDataGridResult selectContentById(int page,int rows,Long categoryId);

}
