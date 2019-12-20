package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
/**
 * @auther: tushengtao
 * @date: 2019/10/19
 * @Description:
 * @param:
 * @return:
 */
    TbItem getItemById(long itemId);
    EUDataGridResult getItemList(int page,int rows);

    /**
     * @author: tushengtao
     * @date: 2019/10/24
     * @Description:  两个表进行操作，商品表 和 商品描述表 使用同一个方法
     * @param:
     * @return:
     */
    TaotaoResult createItem(TbItem item,String desc) throws Exception;
    int deleteByPrimaryKey(Long id);
    TaotaoResult getItemDesc(long itemId);


}
