package com.ui.controllers;

import org.springframework.stereotype.Controller;
import com.ui.model.Employee;
import com.ui.model.EmployeeForm;
import com.ui.service.EmployeeService;
import com.ui.service.CountryService;
import com.ui.util.FINAL;
import com.ui.util.InterMessage;
import com.ui.model.CurrentPage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Map;
@Controller
public class EmployeeController extends ParentController{

    private static final Logger logger= LoggerFactory.getLogger(CustomerController.class);

    public EmployeeController(EmployeeService employeeService,
                              CountryService countryService,
                              MessageSource messageSource){
        super(messageSource);
        this.employeeService= employeeService;
        this.countryService= countryService;
    }
    private InterMessage interMessage;
    private final EmployeeService employeeService;
    private final CountryService countryService;

    @GetMapping("/employee-form-vm")
    public String getEmployeeForm(HttpServletRequest request, Model model){
        String lang= (String)request.getSession().getAttribute(FINAL.LANGUAGE);
        CurrentPage currentPage= new CurrentPage("Employee Form", "pages-jte/employee-form-vm", lang);
        request.getSession().setAttribute(FINAL.CURRENT_PAGE, currentPage);

        EmployeeForm employeeForm= new EmployeeForm();
        employeeForm.setCountries(countryService.getAllCountries());

        //Map<String, String> countries = countryService.getAllCountries();
        model.addAttribute("employeeForm", employeeForm);

        Employee employee= new Employee();
        employee.setBirthDate(new Date(System.currentTimeMillis()));
        employee.setHireDate(new Timestamp(System.currentTimeMillis()));

        model.addAttribute(FINAL.CURRENT_PAGE, currentPage);
        model.addAttribute("employee", employee);

        Locale locale= getLocale(request);
        interMessage= new InterMessage(messageSource, locale);
        model.addAttribute("interMessage", interMessage);

        logger.info("Lang: "+ lang + "  Jte Page Name: "+ currentPage.getJteName());
        return "layout/master-vm";
    }

    @PostMapping("/save-employee-vm")
    public String saveEmployeeVm(HttpServletRequest request, @Valid  @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model){

        CurrentPage currentPage=(CurrentPage)request.getSession().getAttribute(FINAL.CURRENT_PAGE);
        if (bindingResult.hasErrors()) {
            currentPage.setError("Validation errors. Please enter required fields.");
            currentPage.setMessage(null);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("bindingResult", bindingResult);
            Map<String, String> countries = countryService.getAllCountries();
            model.addAttribute("countries", countries);
            logger.error("Validation errors num: employee-form-vm "+ bindingResult.getErrorCount());
            return "layout/master-vm";  // Return to the form with validation errors
        }

        // no validation errors. can save employee
        this.employeeService.save(employee);
        currentPage.setMessage("Employee id: "+ employee.getEmployeeId()+ "  name: "+ employee.getFirstName() + "  has been saved. ");
        currentPage.setError(null);
        model.addAttribute("currentPage", currentPage);
        logger.info("save-employee-vm. "+ currentPage.getMessage());
        return "layout/master-vm";
    }

}
