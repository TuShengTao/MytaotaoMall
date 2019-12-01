package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created on 2019/10/19
 *
 * @author Tu ShengTao
 * Description:
 */
public interface ItemCatService {
    List<EUTreeNode> getCatList(Long parentId);
}
