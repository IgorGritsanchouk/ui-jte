package com.ui.controllers;

import com.ui.model.CustomerOrder;
import com.ui.model.EmployeeOrder;
import com.ui.model.Order;
import com.ui.repository.dao.CustomerOrderRep;
import com.ui.service.*;
import com.ui.util.InterMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerOrderController extends ParentController{

    private static final Logger logger= LoggerFactory.getLogger(CustomerOrderController.class);
    public CustomerOrderController(CustomerOrderRep customerOrderRep,
                                   CustomerOrderService customerOrderService,
                                   EmployeeOrderService employeeOrderService,
                                   CustomerService customerService,
                                   OrderService orderService,
                                   FormService formService,
                                   MessageSource messageSource){
        super(messageSource);
        this.customerOrderRep= customerOrderRep;
        this.customerOrderService= customerOrderService;
        this.employeeOrderService= employeeOrderService;
        this.customerService= customerService;
        this.orderService= orderService;
        this.formService= formService;
    }
    private final CustomerOrderRep customerOrderRep;
    private final CustomerOrderService customerOrderService;
    private final EmployeeOrderService employeeOrderService;
    private final CustomerService customerService;
    private final FormService formService;
    private final OrderService orderService;
    private InterMessage interMessage;

    @GetMapping("customers")
    public void getCustomers() {

        this.customerService.fetchCustomers();
        //return this.customerOrderRep.selectCustomerOrder(Long.getLong("1"));
    }

    @GetMapping("customer-orders")
    public List<CustomerOrder> getCustomerOrders(@RequestParam int employeeId) {

        return this.customerOrderService.callCustomerOrderFunc(employeeId);
    }

    @GetMapping("employee-customer-orders")
    public List<EmployeeOrder> getEmployeeOrders(@RequestParam int employeeId) {

//        List<EmployeeOrder> result= null;
//
//        try{
//            result=this.employeeOrderService.getEmployeeOrderCsr(employeeId);
//        } catch (Exception e){
//            System.out.println("Caught exception : " + e.getMessage());
//        }
//
//        return result;

        //return this.employeeOrderService.getEmployeeOrders().getEmployeeOrderLst(employeeId);

        return this.employeeOrderService.getEmployeeOrders(employeeId);
    }

    @GetMapping("all-orders")
    public List<Order> getAllOrders() {

        return this.orderService.findAll();
    }

}
