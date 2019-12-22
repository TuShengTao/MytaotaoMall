package com.taotao.mapper;

import com.taotao.pojo.Item;


import java.util.List;

/**
 * Created on 2019/11/19
 *
 * @author Tu ShengTao
 * Description:
 */
public interface ItemMapper {
    Item getItemToSolr(Item item);
}
