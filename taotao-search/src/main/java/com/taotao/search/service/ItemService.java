package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.search.pojo.Item;

/**
 * Created on 2019/11/19
 *
 * @author Tu ShengTao
 * Description:
 */
public interface ItemService {
    TaotaoResult importAllItems();
    TaotaoResult addItem(Item item);
}
