package com.taotao.mapper;

import com.taotao.pojo.Order;
import com.taotao.pojo.TbOrder;

import java.util.List;

/**
 * Created on 2019/12/18
 *
 * @author Tu ShengTao
 * Description:
 */
public interface OrderMapper {
    List<Order> selectOrderByUid(TbOrder order);
    int deleteOrderById(TbOrder order);

}
