package com.taotao.search.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2019/11/19
 *
 * @author Tu ShengTao
 * Description: 实现solr  关键字搜索 如： http://localhost:8083/search/query?q=item_title:OT9&page=1&rows=20
 * 接收前台搜索的参数 page rows 查询条件  返回查询结果json
 */
@Controller
@RequestMapping
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "60") Integer rows) {

        if (StringUtils.isBlank(queryString)) {
            return TaotaoResult.build(400, "查询条件不能为空");
        }
        SearchResult searchResult = null;
        try {
            //解决get 参数q  中文参数乱码问题
            queryString=new String(queryString.getBytes("iso8859-1"),"utf-8");
            System.out.println("测试参数问题"+queryString);
            searchResult = searchService.search(queryString, page, rows);
            System.out.println("search结果输出测试：");
            System.out.println(searchResult);

        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok(searchResult);

    }
}
