package com.ls.system.user.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * @Auther: chenjun
 * @Date 2020年01月04日 14:07
 * @Description:
 */
public class Page<T> {
    private Integer offset = 0;
    private Integer limit;
    private Long total;
    private String order;
    private String sort;
    private String pages;
    private Integer page;
    private String pageHtml;

    public String getPageHtml() {
        return pageHtml;
    }

    public void setPageHtml(String pageHtml) {
        this.pageHtml = pageHtml;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    private List<Object> rows;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Object> getRows() {
        return rows;
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }


}