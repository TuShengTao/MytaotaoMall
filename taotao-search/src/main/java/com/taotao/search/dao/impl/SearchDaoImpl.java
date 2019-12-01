package com.taotao.search.dao.impl;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 2019/11/19
 *
 * @author Tu ShengTao
 * Description:
 */
@Service
public class SearchDaoImpl implements SearchDao {

    private HttpSolrClient solrClient;
    @Value("${SOLR.SERVER.URL}")
    private String serverUrl;

    @Override
    public SearchResult search(SolrQuery query)throws Exception {
        solrClient=new HttpSolrClient.Builder(serverUrl).build();

        SearchResult result=new SearchResult();

        QueryResponse queryResponse = solrClient.query(query);
        //获取查询结果
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        //获取高亮显示
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        //发现的条数
        result.setRecordCount(solrDocumentList.getNumFound());

        List<Item> itemList = new ArrayList<>();
        for (SolrDocument solrDocument: solrDocumentList){
            Item item = new Item();
            item.setId((String)solrDocument.get("id") );

            //高亮显示标题
            List<String> highlightList = highlighting.get(solrDocument.get("id")).get("item_title");
            String title = "";
            if (highlightList != null && highlightList.size() > 0) {
                title = highlightList.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }
            //高亮显示标题  title 值带有高亮的标签
            item.setTitle(title);
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((long) solrDocument.get("item_price"));
            item.setSellPoint((String) solrDocument.get("item_sell_point"));
            item.setCategoryName((String) solrDocument.get("item_category_name"));

            itemList.add(item);
        }
        result.setList(itemList);
        return result;
    }
}
