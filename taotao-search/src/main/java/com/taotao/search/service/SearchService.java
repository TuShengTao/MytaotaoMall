package com.taotao.search.service;


import com.taotao.search.pojo.SearchResult;

/**
 * Created on 2019/11/19
 *
 * @author Tu ShengTao
 * Description:
 */
public interface SearchService {
    SearchResult search(String queryString, int page, int rows) throws Exception;

}
