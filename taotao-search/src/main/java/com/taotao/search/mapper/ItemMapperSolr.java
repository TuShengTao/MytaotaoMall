package com.taotao.search.mapper;

import com.taotao.search.pojo.Item;

import java.util.List;

/**
 * Created on 2019/11/19
 *
 * @author Tu ShengTao
 * Description:
 */
public interface ItemMapperSolr {
    List<Item> getItemList();
}
