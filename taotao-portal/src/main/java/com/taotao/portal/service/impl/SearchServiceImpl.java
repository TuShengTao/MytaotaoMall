package com.taotao.portal.service.impl;

import com.taotao.portal.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2019/11/20
 *
 * @author Tu ShengTao
 * Description: 从controller 接收商品的 查询条件 和 页数
 * 然后 利用 httpUtil 到taotao-search 请求数据 返回TaotaoResult 数据类型
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;
    @Override
    public SearchResult searchItemList(String queryString, Integer page) throws Exception {
        //查询参数
        Map<String, String> param = new HashMap<>();
        param.put("q", queryString);
        //page如果为空 就设置为1
        param.put("page", page==null?"1":page.toString());
        //调用taotao-search提供的搜索服务
        String resultString = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
        //转换成taotaoResult对象 此步骤出现错误 转换之后返回了 null
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(resultString, SearchResult.class);

        SearchResult searchResult = null;
        //查询成功
        if (taotaoResult.getStatus() == 200) {
            //取查询结果
            searchResult = (SearchResult) taotaoResult.getData();
        }
        return searchResult;
    }
}
