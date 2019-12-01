package com.taotao.search.pojo;

import java.util.List;

/**
 * Created on 2019/11/19
 *
 * @author Tu ShengTao
 * Description:
 */
public class SearchResult {
    //查询结果列表
    private List<?> list;
    //总记录数
    private long recordCount;
    //总页数
    private long pageCount;
    //当前页
    private long curPage;

    @Override
    public String toString() {
        return "SearchResult{" +
                "list=" + list +
                ", recordCount=" + recordCount +
                ", pageCount=" + pageCount +
                ", curPage=" + curPage +
                '}';
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getCurPage() {
        return curPage;
    }

    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }
}