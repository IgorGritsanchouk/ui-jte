package com.ui.repository;

import com.ui.mapper.EmployeeCustomerOrderMapper;
import com.ui.mapper.OrderRowMapper;
import com.ui.model.EmployeeCustomerOrder;
import com.ui.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpCustOrderRepositoryPage {

    public EmpCustOrderRepositoryPage(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate= jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;

    public List<EmployeeCustomerOrder> getPaginatedOrders(int offset, int limit) {
        String query = "SELECT employee_id, first_name, last_name, mobile_phone, " +
                "order_id, order_date, order_status, tracking_number, " +
                "customer_id, company_name, phone " +
                "FROM employee_customer_order_view LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, new Object[]{limit, offset}, new EmployeeCustomerOrderMapper());
    }

    public int getTotalOrdersCount() {
        String countQuery = "SELECT COUNT(*) FROM orders";
        return jdbcTemplate.queryForObject(countQuery, Integer.class);
    }

}
