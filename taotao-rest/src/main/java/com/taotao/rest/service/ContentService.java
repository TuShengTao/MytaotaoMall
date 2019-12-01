package com.taotao.rest.service;

import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * Created on 2019/11/3
 *
 * @author Tu ShengTao
 * Description:
 */
public interface ContentService {
    List<TbContent> getContenList(long contentId);
}
