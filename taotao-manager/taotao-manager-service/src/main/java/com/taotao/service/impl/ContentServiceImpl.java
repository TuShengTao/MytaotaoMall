package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

/**
 * @author: tushengtao
 * @date: 2019/12/22
 * @Description:  内容管理
 * @param:
 * @return:
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper  contentMapper;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;

	@Override
	public TaotaoResult insertContent(TbContent content) {
		//补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		
		//添加缓存同步逻辑  调用rest服务层
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return TaotaoResult.ok();
	}

	@Override
	public int deleteById(Long id) {
		TbContent content=contentMapper.selectByPrimaryKey(id);
		int flag=contentMapper.deleteByPrimaryKey(id);

		//添加缓存同步逻辑  调用rest服务层
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
/**
 * @author: tushengtao
 * @date: 2019/12/16
 * @Description:  修改首页内容 如 广告轮播图 大广告 小广告
 * @param:
 * @return:
 */
	@Override
	public int updateById(TbContent content) {
		int flag=contentMapper.updateByPrimaryKey(content);
		TbContent content2=contentMapper.selectByPrimaryKey(content.getId());
		//添加缓存同步逻辑  调用rest服务层
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content2.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public EUDataGridResult selectContentById(int page,int rows,Long categoryId) {
		//查询内容列表
		TbContentExample example=new TbContentExample();
		TbContentExample.Criteria criteria=example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExample(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
}
