package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2019/12/11
 *
 * @author Tu ShengTao
 * Description: 查询用户信息 如 地址 address
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/address/{id}")
    @ResponseBody
    public TaotaoResult selectAddress(@PathVariable Long id){
        System.out.println(id);
        TaotaoResult result=userService.getAddress(id);
        System.out.println(result);
        return result;
    }
}
