package com.ui.repository;

import com.ui.model.EmployeeCustomerOrder;
import org.springframework.data.repository.ListCrudRepository;

import java.util.ArrayList;

public interface EmployeeCustomerOrderRepository extends ListCrudRepository<EmployeeCustomerOrder,Long> {

    // can not save, as view is used
}
