package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created on 2019/11/19
 *
 * @author Tu ShengTao
 * Description:
 */
public interface SearchDao {
    SearchResult search(SolrQuery query) throws Exception;
}
