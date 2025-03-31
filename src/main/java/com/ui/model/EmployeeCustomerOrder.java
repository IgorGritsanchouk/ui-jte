package com.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@NoArgsConstructor
@Table("employee_customer_order_view")
public class EmployeeCustomerOrder {

    // employee information
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Long mobilePhone;

    // order information
    private Long orderId;
    private Timestamp orderDate;
    private String orderStatus;
    private String trackingNumber;

    // customer information
    private Long customerId;
    private String companyName;
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeCustomerOrder eco)) return false;
        return Objects.equals(getEmployeeId(), eco.getEmployeeId()) && Objects.equals(getFirstName(), eco.getFirstName()) && Objects.equals(getLastName(), eco.getLastName()) && Objects.equals(getMobilePhone(), eco.getMobilePhone()) && Objects.equals(getOrderId(), eco.getOrderId()) && Objects.equals(getOrderDate(), eco.getOrderDate()) && Objects.equals(getOrderStatus(), eco.getOrderStatus()) && Objects.equals(getTrackingNumber(), eco.getTrackingNumber()) && Objects.equals(getCustomerId(), eco.getCustomerId()) && Objects.equals(getCompanyName(), eco.getCompanyName()) && Objects.equals(getPhone(), eco.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getFirstName(), getLastName(), getMobilePhone(), getOrderId(), getOrderDate(), getOrderStatus(), getTrackingNumber(), getCustomerId(), getCompanyName(), getPhone());
    }

}
