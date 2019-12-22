package com.taotao.search.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.mapper.ItemMapperSolr;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2019/11/19
 *
 * @author Tu ShengTao
 * Description: 从数据库 MySQL 获得商品数据 然后导入 solr 索引库
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapperSolr itemMapperSolr;

    private HttpSolrClient solrClient;
    @Value("${SOLR.SERVER.URL}")
    private String serverUrl;

    @Override
    public TaotaoResult importAllItems() {
        solrClient=new HttpSolrClient.Builder(serverUrl).build();
        List<Item> itemList = itemMapperSolr.getItemList();

        try{
            //将商品写入solr的索引
            for(Item item :itemList){
                SolrInputDocument document=new SolrInputDocument();

                document.setField("id", item.getId());
                document.setField("item_title", item.getTitle());
                document.setField("item_sell_point", item.getSellPoint());
                document.setField("item_price", item.getPrice());
                document.setField("item_image", item.getImage());
                document.setField("item_category_name", item.getCategoryName());
                document.setField("item_desc", item.getItemDesc());

                solrClient.add(document);
            }
            //提交修改
            solrClient.commit();

        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }
/**
 * @author: tushengtao
 * @date: 2019/12/20
 * @Description:  后台系统 添加单个商品  taotao-manger 调用
 * @param:
 * @return:
 */
    @Override
    public TaotaoResult addItem(Item item) {
        solrClient=new HttpSolrClient.Builder(serverUrl).build();
        try{
            //将商品写入solr的索引
                SolrInputDocument document=new SolrInputDocument();
                document.setField("id", item.getId());
                document.setField("item_title", item.getTitle());
                document.setField("item_sell_point", item.getSellPoint());
                document.setField("item_price", item.getPrice());
                document.setField("item_image", item.getImage());
                document.setField("item_category_name", item.getCategoryName());
                document.setField("item_desc", item.getItemDesc());

                solrClient.add(document);
                //提交修改
                solrClient.commit();
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();

    }
}
