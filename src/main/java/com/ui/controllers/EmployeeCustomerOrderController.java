package com.ui.controllers;

import com.ui.model.*;
import com.ui.result.EmployeeCustomerOrderResult;
import com.ui.service.*;
import com.ui.util.FINAL;
import com.ui.util.InterMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Controller
public class EmployeeCustomerOrderController extends ParentController{
    private final Logger logger = LoggerFactory.getLogger(EmployeeCustomerOrderController.class);
    public EmployeeCustomerOrderController(MessageSource messageSource,
                                           EmployeeCustomerOrderService ecos,
                                           FormService formService,
                                           OrderService orderService,
                                           CustomerService customerService,
                                           EmployeeService employeeService){
        super(messageSource);
        this.employeeCustomerOrderService= ecos;
        this.orderService= orderService;
        this.formService= formService;
        this.customerService= customerService;
        this.employeeService= employeeService;
    }
    private InterMessage interMessage;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final FormService formService;
    private final EmployeeCustomerOrderService employeeCustomerOrderService;
    private final EmployeeService employeeService;

    @GetMapping("/ec-orders-vm")
    public String geItemList(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "5") int size,
                             HttpServletRequest request, Model model){

        String lang= (String)request.getSession().getAttribute(FINAL.LANGUAGE);
        CurrentPage currentPage= new CurrentPage("Customer orders with employee allocation", "pages-jte/ec-orders-vm", lang);
        request.getSession().setAttribute(FINAL.CURRENT_PAGE, currentPage);

        EmployeeCustomerOrderResult ecOrderPaginationResult= this.employeeCustomerOrderService.getPaginatedOrders(page, size);
        model.addAttribute(FINAL.CURRENT_PAGE, currentPage);
        model.addAttribute("ecOrderPaginationResult", ecOrderPaginationResult);

        Locale locale= getLocale(request);
        interMessage= new InterMessage(messageSource, locale);
        model.addAttribute("interMessage", interMessage);

        logger.info("Lang: "+ lang + "  Jte Page Name: "+ currentPage.getJteName());
        return "layout/master-vm";
    }

// old version with list rather than pagination
//    @GetMapping("/ec-orders-vm")
//    public String getOrderList(HttpServletRequest request, Model model){
//
//        String lang= (String)request.getSession().getAttribute(FINAL.LANGUAGE);
//        CurrentPage currentPage= new CurrentPage("Customer orders with employee allocation", "pages-jte/ec-orders-vm", lang);
//        request.getSession().setAttribute(FINAL.CURRENT_PAGE, currentPage);
//
//        List<EmployeeCustomerOrder> ecOrdersLst= this.employeeCustomerOrderService.findAll();
//        model.addAttribute(FINAL.CURRENT_PAGE, currentPage);
//        model.addAttribute("ecOrdersLst", ecOrdersLst);
//
//        Locale locale= getLocale(request);
//        interMessage= new InterMessage(messageSource, locale);
//        model.addAttribute("interMessage", interMessage);
//
//        logger.info("Lang: "+ lang + "  Jte Page Name: "+ currentPage.getJteName());
//        return "layout/master-vm";
//    }

    @GetMapping("find-order-vm")
    public String getOrderForId(@RequestParam long orderId, HttpServletRequest request, Model model) {
        String lang= (String)request.getSession().getAttribute(FINAL.LANGUAGE);
        CurrentPage currentPage= new CurrentPage("Order Form", "pages-jte/order-form-vm", lang);
        request.getSession().setAttribute(FINAL.CURRENT_PAGE, currentPage);

        Map<String, String> countries = formService.getAllCountries();
        model.addAttribute("countries", countries);

        //Order order= this.orderService.findByOrderId(orderId);
        Optional<Order> optional= this.orderService.findById(orderId);
        Order order= optional.get();

        OrderForm orderForm= new OrderForm();
        orderForm.setCountries(formService.getAllCountries());
        orderForm.setCustomerDropDown(orderService.getCustomerDropDown());
        orderForm.setEmployeeDropDown(orderService.getEmployeeDropDown());

        model.addAttribute(FINAL.CURRENT_PAGE, currentPage);
        model.addAttribute("orderForm", orderForm);
        model.addAttribute("order", order);

        Locale locale= getLocale(request);
        interMessage= new InterMessage(messageSource, locale);
        model.addAttribute("interMessage", interMessage);

        logger.info("Controller. Lang: "+ lang + "  Jte Page Name: "+ currentPage.getJteName());
        return "layout/master-vm";
    }

    @GetMapping("find-customer-vm")
    public String getCustomerForId(@RequestParam long customerId, HttpServletRequest request, Model model) {

        String lang= (String)request.getSession().getAttribute(FINAL.LANGUAGE);
        CurrentPage currentPage= new CurrentPage("Customer Form", "pages-jte/customer-form-vm", lang);
        request.getSession().setAttribute(FINAL.CURRENT_PAGE, currentPage);

        Map<String, String> countries = formService.getAllCountries();
        model.addAttribute("countries", countries);

        model.addAttribute(FINAL.CURRENT_PAGE, currentPage);
        Optional<Customer> optional=customerService.findForId(customerId);
        model.addAttribute("customer", optional.get());

        Locale locale= getLocale(request);
        interMessage= new InterMessage(messageSource, locale);
        model.addAttribute("interMessage", interMessage);

        logger.info("Lang: "+ lang + "  Jte Page Name: "+ currentPage.getJteName());
        return "layout/master-vm";

    }


    @GetMapping("find-employee-vm")
    public String getEmployeeForId(@RequestParam long employeeId, HttpServletRequest request, Model model) {
        String lang= (String)request.getSession().getAttribute(FINAL.LANGUAGE);
        CurrentPage currentPage= new CurrentPage("Order Form", "pages-jte/employee-form-vm", lang);
        request.getSession().setAttribute(FINAL.CURRENT_PAGE, currentPage);

        Map<String, String> countries = formService.getAllCountries();
        model.addAttribute("countries", countries);

        //Order order= this.orderService.findByOrderId(orderId);
        Optional<Employee> optional= this.employeeService.findForId(employeeId);
        Employee employee= optional.get();

        EmployeeForm employeeForm= new EmployeeForm();
        employeeForm.setCountries(formService.getAllCountries());
        employeeForm.setRegions(formService.getAllRegions());
        employeeForm.setTitles(formService.getAllTitles());
        employeeForm.setEmployeeDropDown(employeeService.getEmployeeDropDown());

        model.addAttribute(FINAL.CURRENT_PAGE, currentPage);
        model.addAttribute("employeeForm", employeeForm);
        model.addAttribute("employee", employee);

        Locale locale= getLocale(request);
        interMessage= new InterMessage(messageSource, locale);
        model.addAttribute("interMessage", interMessage);

        logger.info("Controller. Lang: "+ lang + "  Jte Page Name: "+ currentPage.getJteName());
        return "layout/master-vm";
    }
}
