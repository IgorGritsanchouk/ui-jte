package com.ui.service;

import com.ui.model.EmployeeCustomerOrder;
import com.ui.repository.EmployeeCustomerOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCustomerOrderService {

    private Logger logger= LoggerFactory.getLogger(EmployeeCustomerOrderService.class);

    private EmployeeCustomerOrderRepository employeeCustomerOrderRepository;

    public EmployeeCustomerOrderService(EmployeeCustomerOrderRepository employeeCustomerOrderRepository){

        this.employeeCustomerOrderRepository= employeeCustomerOrderRepository;
    }

    public List<EmployeeCustomerOrder> findAll(){
       return this.employeeCustomerOrderRepository.findAll();
    }

}
