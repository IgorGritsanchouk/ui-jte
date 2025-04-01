package com.ui.controllers;

import com.ui.model.*;
import com.ui.service.CountryService;
import com.ui.service.CustomerService;
import com.ui.service.EmployeeCustomerOrderService;
import com.ui.service.OrderService;
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

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Controller
public class EmployeeCustomerOrderController extends ParentController{
    private final Logger logger = LoggerFactory.getLogger(EmployeeCustomerOrderController.class);
    public EmployeeCustomerOrderController(MessageSource messageSource,
                                           EmployeeCustomerOrderService ecos,
                                           CountryService countryService,
                                           OrderService orderService,
                                           CustomerService customerService){
        super(messageSource);
        this.employeeCustomerOrderService= ecos;
        this.orderService= orderService;
        this.countryService= countryService;
        this.customerService= customerService;
    }
    private InterMessage interMessage;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final CountryService countryService;
    private final EmployeeCustomerOrderService employeeCustomerOrderService;

    @GetMapping("/ec-orders-vm")
    public String getOrderList(HttpServletRequest request, Model model){

        String lang= (String)request.getSession().getAttribute(FINAL.LANGUAGE);
        CurrentPage currentPage= new CurrentPage("Customer orders with employee allocation", "pages-jte/ec-orders-vm", lang);
        request.getSession().setAttribute(FINAL.CURRENT_PAGE, currentPage);

        List<EmployeeCustomerOrder> ecOrdersLst= this.employeeCustomerOrderService.findAll();
        model.addAttribute(FINAL.CURRENT_PAGE, currentPage);
        model.addAttribute("ecOrdersLst", ecOrdersLst);

        Locale locale= getLocale(request);
        interMessage= new InterMessage(messageSource, locale);
        model.addAttribute("interMessage", interMessage);

        logger.info("Lang: "+ lang + "  Jte Page Name: "+ currentPage.getJteName());
        return "layout/master-vm";
    }

    @GetMapping("find-order-vm")
    public String getOrderForId(@RequestParam long orderId, HttpServletRequest request, Model model) {
        String lang= (String)request.getSession().getAttribute(FINAL.LANGUAGE);
        CurrentPage currentPage= new CurrentPage("Order Form", "pages-jte/order-form-vm", lang);
        request.getSession().setAttribute(FINAL.CURRENT_PAGE, currentPage);

        Map<String, String> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);

        //Order order= this.orderService.findByOrderId(orderId);
        Optional<Order> optional= this.orderService.findById(orderId);
        Order order= optional.get();

        OrderForm orderForm= new OrderForm();
        orderForm.setCountries(countryService.getAllCountries());
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

        Map<String, String> countries = countryService.getAllCountries();
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


}
