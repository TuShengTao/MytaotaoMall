package com.taotao.portal.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2019/11/28
 *
 * @author Tu ShengTao
 * Description:
 */
@Controller
public class TurnPageController {
    //后端路由控制
    //首页跳转到搜索页

    @RequestMapping("/superMarket/{pageName}")
    public String turnOtherPage(@PathVariable String pageName){
        System.out.println("输出要跳转的页面："+pageName);
        return pageName;
    }
    //搜索页 点击某个商品 跳转到 商品详情页

    @RequestMapping("/superMarket/item/{itemId}")
    public String showItem() {
        return "item";
    }
}
