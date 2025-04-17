package com.ui.controllers;

import com.ui.model.CurrentPage;
import com.ui.model.Customer;
import com.ui.util.FINAL;
import com.ui.util.InterMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

@Controller
public class CustomAccessDeniedHandlerController implements AccessDeniedHandler {

    private static final Logger logger= LoggerFactory.getLogger(CustomAccessDeniedHandlerController.class);

    @GetMapping("/login-form-vm")
    public String getCustomerForm(HttpServletRequest request, Model model){
        String lang= (String)request.getSession().getAttribute(FINAL.LANGUAGE);
        CurrentPage currentPage= new CurrentPage("Customer Form", "pages-jte/login-form-vm", lang);
//        request.getSession().setAttribute(FINAL.CURRENT_PAGE, currentPage);
//
//        Map<String, String> countries = formService.getAllCountries();
//        model.addAttribute("countries", countries);
//
//        model.addAttribute(FINAL.CURRENT_PAGE, currentPage);
//        model.addAttribute("customer", new Customer());
//
//        Locale locale= getLocale(request);
//        interMessage= new InterMessage(messageSource, locale);
//        model.addAttribute("interMessage", interMessage);

        logger.info("Lang: "+ lang + "  Jte Page Name: "+ currentPage.getJteName());
        return "layout/master-vm";
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<h1>403 - Access Denied</h1><p>You do not have permission to access this resource.</p>");
        response.getWriter().write("<br><a href=\"/login-form-vm\" ></a>");
    }
}
