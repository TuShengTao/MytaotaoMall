package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * Created on 2019/10/19
 *
 * @author Tu ShengTao
 * Description:
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    /**
     * @auther: tushengtao
     * @date: 2019/10/19
     * @Description: 返回json数据
     * @param: [parentId]
     * @return: java.util.List<com.taotao.common.pojo.EUTreeNode>
     */
    @ResponseBody
    private List<EUTreeNode> getCatList(@RequestParam(value="id",defaultValue="0")Long parentId) {
        List<EUTreeNode> list = itemCatService.getCatList(parentId);
        return list;
    }
}
