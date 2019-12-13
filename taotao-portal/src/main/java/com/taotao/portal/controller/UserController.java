package com.taotao.portal.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2019/12/11
 *
 * @author Tu ShengTao
 * Description:
 */
@Controller

public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/user/address",produces= MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getAddress(Long id){
        TbUser user=userService.selectAddress(id);
        String result= JsonUtils.objectToJson(user);
        return result;
    }
}
