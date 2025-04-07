package com.ui.mapper;

import com.ui.model.EmployeeCustomerOrder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeCustomerOrderMapper implements RowMapper<EmployeeCustomerOrder> {

    @Override
    public EmployeeCustomerOrder mapRow(ResultSet rs, int rowNum) throws SQLException {

        EmployeeCustomerOrder eco = new EmployeeCustomerOrder();
        eco.setEmployeeId(rs.getLong("employee_id"));
        eco.setFirstName(rs.getString("first_name"));
        eco.setLastName(rs.getString("last_name"));
        eco.setMobilePhone(rs.getLong("mobile_phone"));
        eco.setOrderId(rs.getLong("order_id"));
        eco.setOrderDate(rs.getTimestamp("order_date"));
        eco.setOrderStatus(rs.getString("order_status"));
        eco.setTrackingNumber(rs.getString("tracking_number"));
        eco.setCustomerId(rs.getLong("customer_id"));
        eco.setCompanyName(rs.getString("company_name"));
        eco.setPhone(rs.getString("phone"));

        return eco;
    }

}
