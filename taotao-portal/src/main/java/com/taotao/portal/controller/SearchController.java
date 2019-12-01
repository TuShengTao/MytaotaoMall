package com.taotao.portal.controller;
import com.taotao.common.utils.JsonUtils;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 2019/11/20
 *
 * @author Tu ShengTao
 * Description:
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/searchByAjax",method = RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public String searchItemList(@RequestParam
           (value="queryString",defaultValue = "null.html")String queryString, @RequestParam(value="curPage",defaultValue = "1")Integer curPage) throws Exception {
            SearchResult searchResult = searchService.searchItemList(queryString, curPage);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("itemList", searchResult.getItemList());
            jsonObject.put("query", queryString);
            jsonObject.put("totalPages", searchResult.getPageCount());
            jsonObject.put("page", searchResult.getCurPage());
            jsonObject.put("pages", searchResult.getPageCount());
            System.out.println(searchResult.getItemList());
            System.out.println(jsonObject);
            //实际上是转换为string ,此注解@ResponseBody 再转为 ajax 要求返回的json
            String data= JsonUtils.objectToJson(jsonObject);
            return data;
    }

}
