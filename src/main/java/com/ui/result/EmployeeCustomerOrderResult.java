package com.ui.result;

import com.ui.model.EmployeeCustomerOrder;

import java.util.List;

public class EmployeeCustomerOrderResult {
    private List<EmployeeCustomerOrder> orders;
    private int total;
    private int totalPages;
    private int currentPage;
    private int pageSize;

    public EmployeeCustomerOrderResult(List<EmployeeCustomerOrder> orders, int total, int totalPages, int currentPage, int pageSize) {
        this.orders = orders;
        this.total = total;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public List<EmployeeCustomerOrder> getOrders() {
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

