package com.ui.service;

import com.ui.mapper.OrderRowMapper;
import com.ui.model.Customer;
import com.ui.model.Employee;
import com.ui.model.Item;
import com.ui.model.Order;
import com.ui.repository.*;
import com.ui.result.EmployeeCustomerOrderResult;
import com.ui.result.OrderPaginatedResult;
import com.ui.result.PaginatedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository,
                        OrderRepositoryPage orderRepositoryPage,
                        CustomerRepository customerRepository,
                        EmployeeRepository employeeRepository,
                        JdbcTemplate jdbcTemplate){

        this.orderRepository= orderRepository;
        this.orderRepositoryPage= orderRepositoryPage;
        this.customerRepository= customerRepository;
        this.employeeRepository= employeeRepository;
        this.jdbcTemplate= jdbcTemplate;
    }

    private OrderRepository orderRepository;
    private EmployeeRepository employeeRepository;
    private CustomerRepository customerRepository;
    private OrderRepositoryPage orderRepositoryPage;

    private JdbcTemplate jdbcTemplate;

    public void save(Order order){

        Order saved= this.orderRepository.save(order);
        logger.info("Saved order ok: "+ saved.getOrderId());
    }

    public Order findByOrderId(Long id) {

        String sql = "SELECT * FROM ORDERS WHERE order_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new OrderRowMapper());
    }

    public Optional<Order> findById(Long id){
        return orderRepository.findById(id);
    }
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public OrderPaginatedResult getPaginatedOrders(int page, int size) {
        int offset = (page - 1) * size;
        List<Order> orders= orderRepositoryPage.getPaginatedOrders(offset, size);
        int totalItems = orderRepositoryPage.getTotalOrdersCount();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        return new OrderPaginatedResult(orders, totalItems, totalPages, page, size);
    }

    public void create(Order o) {

        Long nextOrderId = jdbcTemplate.queryForObject("SELECT nextval('orders_order_id_seq')", Long.class);
        //String sql = "INSERT INTO customers (customer_id, company_name, contact_name, contact_title, address, city, region, postal_code, country, phone ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sql = "INSERT INTO orders (order_id, customer_id, employee_id, order_date, required_date, shipped_date, shipped_via, shipp_name, shipp_address, shipp_city, shipp_region," +
                "shipp_postal_code, shipping_country, order_status, total_amount, payment_method, payment_status, tracking_number ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, nextOrderId, o.getCustomerId(), o.getEmployeeId(), o.getOrderDate(), o.getRequiredDate(), o.getShippedDate(),
                o.getShippedVia(), o.getShippName(), o.getShippAddress(), o.getShippCity(), o.getShippRegion(), o.getShippPostalCode(), o.getShippingCountry(),
                o.getOrderStatus(), o.getTotalAmount(), o.getPaymentMethod(), o.getPaymentStatus(), o.getTrackingNumber());
    }

    public Map<Long, String> getEmployeeDropDown(){

        List<Employee> allEmployees= this.employeeRepository.findAll();

        Map<Long, String> empDropDown= new HashMap();

        for(Iterator<Employee> e = allEmployees.iterator(); e.hasNext(); ) {
            Employee employee = e.next();
            empDropDown.put(employee.getEmployeeId(), employee.getFirstName()+" "+ employee.getLastName());
        }

        return empDropDown;
    }

    public Map<Long, String> getCustomerDropDown(){

        List<Customer> allCustomers= this.customerRepository.findAll();
        Map<Long, String> cusDropDown= new HashMap();

        for(Iterator<Customer> c = allCustomers.iterator(); c.hasNext(); ) {
            Customer customer = c.next();
            cusDropDown.put(customer.getCustomerId(), customer.getContactName()+" - "+ customer.getPostalCode());
        }

        return cusDropDown;
    }

}
