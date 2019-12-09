package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2019/11/28
 *
 * @author Tu ShengTao
 * Description: 前后端分离 解决页面跳转问题 后端路由控制
 */
@Controller
public class TurnPageController {


    //首页跳转到搜索页

    @RequestMapping("/superMarket/{pageName}")
    public String turnOtherPage(@PathVariable String pageName){
        return pageName;
    }
    //搜索页 点击某个商品 跳转到 商品详情页 item.html

    @RequestMapping("/superMarket/item/{itemId}")
    public String showItem() {
        return "item";
    }

    // 跳转到 我的购物车页面 myCart.html

    @RequestMapping("/superMarket/myCart")
    public String showCart() {
        return "myCart";
    }

}
