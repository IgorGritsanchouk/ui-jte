package com.ui.result;

import com.ui.model.Order;

import java.util.List;

public class OrderPaginatedResult {
    private List<Order> orders;
    private int total;
    private int totalPages;
    private int currentPage;
    private int pageSize;

    public OrderPaginatedResult(List<Order> orders, int total, int totalPages, int currentPage, int pageSize) {
        this.orders = orders;
        this.total = total;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public List<Order> getOrders() {
        return orders;
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

