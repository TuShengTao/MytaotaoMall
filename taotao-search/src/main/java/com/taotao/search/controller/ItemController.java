package com.taotao.search.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2019/11/19
 *
 * @author Tu ShengTao
 * Description: 实现 mysql里的商品部分数据导入 solr索引库
 */
@Controller
@RequestMapping("/manager")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/importall")
    @ResponseBody
    public TaotaoResult importAllItems() {
        TaotaoResult result = itemService.importAllItems();
        return result;
    }
    @RequestMapping(value = "/addItem",method =RequestMethod.POST )
    @ResponseBody
    public TaotaoResult addItem(@RequestBody String param) {
        Item item= JsonUtils.jsonToPojo(param,Item.class);
        TaotaoResult result = itemService.addItem(item);
        return result;
    }
}
