package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2019/11/5
 *
 * @author Tu ShengTao
 * Description: 后台管理系统修改首页内容时 发布一个服务 传入修改的id
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/content/{contentCid}")
    @ResponseBody
    public TaotaoResult contentCacheSync(@PathVariable Long contentCid){
        TaotaoResult result=redisService.syncContent(contentCid);
        return result;
    }

}
