package com.ui.repository;

import com.ui.mapper.OrderRowMapper;
import com.ui.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OrderRepositoryPage {

    public OrderRepositoryPage(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate= jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;

    public List<Order> getPaginatedOrders(int offset, int limit) {
        String query = "SELECT order_id, customer_id, employee_id, order_date, required_date, shipped_date, " +
                "shipped_via, shipp_name, shipp_address, shipp_city, shipp_region, shipp_postal_code, " +
                "shipping_counry, order_status, total_amount, payment_method, payment_status, tracking_number " +
                "FROM orders LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, new Object[]{limit, offset}, new OrderRowMapper());
    }

    public int getTotalOrdersCount() {
        String countQuery = "SELECT COUNT(*) FROM orders";
        return jdbcTemplate.queryForObject(countQuery, Integer.class);
    }

}
