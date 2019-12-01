package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created on 2019/11/3
 *
 * @author Tu ShengTao
 * Description:
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;
    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public TaotaoResult getContentList(@PathVariable Long contentCategoryId){
        try {
            List<TbContent> list=contentService.getContenList(contentCategoryId);
            //返回自定义 数据格式
            return TaotaoResult.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            //返回错误消息
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }


}
