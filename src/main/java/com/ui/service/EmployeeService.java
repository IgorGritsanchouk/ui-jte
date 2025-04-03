package com.ui.service;

import com.ui.model.Employee;
import com.ui.repository.CustomerRepository;
import com.ui.repository.EmployeeRepository;
import com.ui.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import com.ui.model.Customer;
import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(
                        EmployeeRepository employeeRepository,
                        JdbcTemplate jdbcTemplate){

        this.employeeRepository= employeeRepository;
        this.jdbcTemplate= jdbcTemplate;
    }

    private EmployeeRepository employeeRepository;
    private JdbcTemplate jdbcTemplate;

    public Optional<Employee> findForId(Long id){
        logger.info("Find Employee for id: "+ id);
        return employeeRepository.findById(id);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public void save(Employee employee){
        Employee result= this.employeeRepository.save(employee);
        logger.info("Saved employee id: "+ result.getEmployeeId()+" name: "+ result.getFirstName());
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

}
