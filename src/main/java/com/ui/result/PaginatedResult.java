package com.ui.result;

import com.ui.model.Item;
import java.util.List;

public class PaginatedResult {
    private List<Item> items;
    private int total;
    private int totalPages;
    private int currentPage;
    private int pageSize;

    public PaginatedResult(List<Item> items, int total, int totalPages, int currentPage, int pageSize) {
        this.items = items;
        this.total = total;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public List<Item> getItems() {
        return items;
    }
    public int getTotal() {
        return total;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public int getPageSize() {
        return pageSize;
    }
}

