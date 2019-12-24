package com.aoeai.lcsr.utils.page;

import java.util.List;

/**
 * 分页小能手
 */
public class PageHelper {

    public static final Pagination buildPagination(Page page, List records){
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(page.getCurrentNum());
        pagination.setPageSize(page.getPageSize());
        pagination.setRecords(records);
        pagination.setTotalCount(page.getTotalCount());
        pagination.setTotalPages(page.getTotalPages());

        return pagination;
    }

}
