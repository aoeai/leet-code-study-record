package com.aoeai.lcsr.utils.page;

import java.util.List;

/**
 * 分页对象
 */
public class Pagination<T> {

    /**
     * 每页显示的记录数
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private int totalCount;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 当前页
     */
    private int currentPage = 1;

    /**
     * 分页数据
     */
    private List<T> records;

    public int getPageSize() {
        return pageSize;
    }

    public Pagination<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public Pagination<T> setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public Pagination<T> setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public Pagination<T> setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public List<T> getRecords() {
        return records;
    }

    public Pagination<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }
}
