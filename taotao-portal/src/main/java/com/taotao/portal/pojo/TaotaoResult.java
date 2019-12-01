package com.taotao.portal.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 淘淘商城自定义响应结构
 */
public class TaotaoResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static TaotaoResult build(Integer status, String msg, Object data) {
        return new TaotaoResult(status, msg, data);
    }

    public static TaotaoResult ok(Object data) {
        return new TaotaoResult(data);
    }

    public static TaotaoResult ok() {
        return new TaotaoResult(null);
    }

    public TaotaoResult() {

    }

    public static TaotaoResult build(Integer status, String msg) {
        return new TaotaoResult(status, msg, null);
    }

    public TaotaoResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public TaotaoResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     *
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static TaotaoResult formatToPojo(String jsonData, Class<?> clazz) {

        try {
            Object obj =new SearchResult();
            List<Item>itemList=new ArrayList<>();
            // String  转为  jsonNode
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            //获取data
            JsonNode data = jsonNode.get("data");
            //获取data的list
            JsonNode list=data.get("list");
            // //获取data的recordCount  jsonNode 先转 String  再转为 long
            long recordCount=Long.parseLong(MAPPER.writeValueAsString(data.get("recordCount")));
            //获取data的pageCount
            long pageCount=Long.parseLong(MAPPER.writeValueAsString(data.get("pageCount")));
            //获取data的curPage
            long curPage= Long.parseLong(MAPPER.writeValueAsString(data.get("curPage")));
            // 遍历 list Node
            for (JsonNode itemNode:list){
                Item item =new Item();
                System.out.println(itemNode);
                //设置商品id
                System.out.println(MAPPER.writeValueAsString(itemNode.get("id")));
                System.out.println(MAPPER.writeValueAsString(itemNode.get("categoryName")));
                System.out.println(Long.parseLong(MAPPER.writeValueAsString(itemNode.get("price"))));
                item.setId(MAPPER.writeValueAsString(itemNode.get("id")));
                item.setCategory_name(MAPPER.writeValueAsString(itemNode.get("categoryName")));
                item.setImage(MAPPER.writeValueAsString(itemNode.get("image")));
                item.setItem_des(MAPPER.writeValueAsString(itemNode.get("itemDesc")));
                item.setSell_point(MAPPER.writeValueAsString(itemNode.get("sellPoint")));
                item.setTitle(MAPPER.writeValueAsString(itemNode.get("title")));
                item.setPrice(Long.parseLong(MAPPER.writeValueAsString(itemNode.get("price"))));
                itemList.add(item);
            }
            //set  obj 对象
            ((SearchResult) obj).setItemList(itemList);
            ((SearchResult) obj).setCurPage(curPage);
            ((SearchResult) obj).setPageCount(pageCount);
            ((SearchResult) obj).setRecordCount(recordCount);
            return build(Integer.valueOf(MAPPER.writeValueAsString(jsonNode.get("status"))), MAPPER.writeValueAsString(jsonNode.get("msg")), obj);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 没有object对象的转化
     *
     * @param json
     * @
     */
    public static TaotaoResult format(String json) {
        try {
            return MAPPER.readValue(json, TaotaoResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static TaotaoResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
