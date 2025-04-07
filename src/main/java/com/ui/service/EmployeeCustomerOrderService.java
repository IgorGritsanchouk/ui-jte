package com.ui.service;

import com.ui.model.EmployeeCustomerOrder;
import com.ui.model.Order;
import com.ui.repository.EmpCustOrderRepositoryPage;
import com.ui.repository.EmployeeCustomerOrderRepository;
import com.ui.result.EmployeeCustomerOrderResult;
import com.ui.result.OrderPaginatedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCustomerOrderService {

    private Logger logger= LoggerFactory.getLogger(EmployeeCustomerOrderService.class);
    public EmployeeCustomerOrderService(EmployeeCustomerOrderRepository employeeCustomerOrderRepository,
                                        EmpCustOrderRepositoryPage empCustOrderRepositoryPage){

        this.employeeCustomerOrderRepository= employeeCustomerOrderRepository;
        this.empCustOrderRepositoryPage= empCustOrderRepositoryPage;
    }

    private EmployeeCustomerOrderRepository employeeCustomerOrderRepository;
    private final EmpCustOrderRepositoryPage empCustOrderRepositoryPage;
    public List<EmployeeCustomerOrder> findAll(){
       return this.employeeCustomerOrderRepository.findAll();
    }

    public EmployeeCustomerOrderResult getPaginatedOrders(int page, int size) {

        int offset = (page - 1) * size;
        List<EmployeeCustomerOrder> ecOrders= empCustOrderRepositoryPage.getPaginatedOrders(offset, size);
        int total = empCustOrderRepositoryPage.getTotalOrdersCount();
        int totalPages = (int) Math.ceil((double) total / size);

        return new EmployeeCustomerOrderResult(ecOrders, total, totalPages, page, size);
    }

}
