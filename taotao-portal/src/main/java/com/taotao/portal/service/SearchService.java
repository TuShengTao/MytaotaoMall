package com.taotao.portal.service;


import com.taotao.portal.pojo.SearchResult;

/**
 * Created on 2019/11/20
 *
 * @author Tu ShengTao
 * Description:
 */
public interface SearchService {
    SearchResult searchItemList(String queryString, Integer page) throws Exception;
}
