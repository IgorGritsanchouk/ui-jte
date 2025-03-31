package com.ui.controllers;

import com.ui.model.CurrentPage;
import com.ui.model.Customer;
import com.ui.model.EmployeeCustomerOrder;
import com.ui.service.CountryService;
import com.ui.service.CustomerService;
import com.ui.service.EmployeeCustomerOrderService;
import com.ui.util.FINAL;
import com.ui.util.InterMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class EmployeeCustomerOrderController extends ParentController{
    private final Logger logger = LoggerFactory.getLogger(EmployeeCustomerOrderController.class);
    public EmployeeCustomerOrderController(MessageSource messageSource,
                                           EmployeeCustomerOrderService ecos){
        super(messageSource);
        this.employeeCustomerOrderService= ecos;
    }
    private InterMessage interMessage;
    private final EmployeeCustomerOrderService employeeCustomerOrderService;

    @GetMapping("/ec-order-list-vm")
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

//    @GetMapping("/sp-orders-vm")
//    public String getOrderStoredProcedure(HttpServletRequest request, Model model){
//
//        String lang= (String)request.getSession().getAttribute(FINAL.LANGUAGE);
//        CurrentPage currentPage= new CurrentPage("Order List", "pages-jte/ec-orders-vm", lang);
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

}
