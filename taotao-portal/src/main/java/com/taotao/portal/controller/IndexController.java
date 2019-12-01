package com.taotao.portal.controller;

import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 2019/10/31
 *
 * @author Tu ShengTao
 * Description: 展示首页
 */
@Controller
public class IndexController {
    @Autowired
    private ContentService contentService;
    @RequestMapping("/index")
    public ModelAndView showIndex() {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/indexAd")
    @ResponseBody
    public String showAd() {
        /**
         * @author: tushengtao
         * @date: 2019/11/12
         * @Description: 展示首页大广告轮播图 返回json
         * @param: [model]
         * @return: java.lang.String
         */
        String adJson =contentService.getContentList();
        return adJson;
    }
    /**
     * @author: tushengtao
     * @date: 2019/11/3
     * @Description: 测试 httpclient 的 post 请求
     * @param:
     * @return:
     */
    // produces 解决客户端乱码 produces = MediaType

    @RequestMapping(value = "/httpclient/post",method = RequestMethod.POST,produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String testPost(String username,String password){
        String result="OK:    "+username+"    "+password;
        return result;
    }
}
