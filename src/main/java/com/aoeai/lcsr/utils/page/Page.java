package com.aoeai.lcsr.utils.page;

/**
 *
 * 分页对象
 */
public class Page {

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
    private int currentNum = 1;

    /**
     * Mysql limit 第一个参数
     */
    private int offset;

    public Page(int pageSize, int totalCount){
        if (pageSize < 1){
            pageSize = 1;
        }
        if(totalCount < 0){
            totalCount = 0;
        }

        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPages() {
        // 其中 pageSize  - 1 就是 totalCount / pageSize的最大的余数
        totalPages = (totalCount + pageSize -1) / pageSize;
        totalPages = totalPages < 1 ? 1 : totalPages;
        return totalPages;
    }

    public int getOffset() {
        offset = (currentNum - 1) * pageSize;
        return offset;
    }

    public Page setCurrentNum(int currentNum) {
        if(currentNum > getTotalPages()){
            currentNum = getTotalPages();
        }
        if(currentNum < 1){
            currentNum =1;
        }
        this.currentNum = currentNum;

        return this;
    }

    public int getCurrentNum() {
        return currentNum;
    }


}
