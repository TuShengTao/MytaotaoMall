package com.taotao.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created on 2019/10/31
 *
 * @author Tu ShengTao
 * Description:
 */
public class CatNode {
    //@JsonProperty ： 转为json数据格式 重命名作用

    @JsonProperty("n")
    private String name;
    @JsonProperty("u")
    private String url;
    @JsonProperty("i")
    private List<?> item;
    //?所有类型


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "CatNode{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", item=" + item +
                '}';
    }
}
