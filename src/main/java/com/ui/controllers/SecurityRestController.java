package com.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityRestController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/permitted")
    public String permitted() {

        StringBuffer sb= new StringBuffer();
        for(String beanName : applicationContext.getBeanDefinitionNames()){
            sb.append( beanName+ "  ");
        }

        return "Hello, Access Permitted! "+ sb.toString();
    }

    @GetMapping("/home")
    public String home() {
        return "Hello, Home!";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/manager-access")
    public String manager() {
        return "Hello, Manager!";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user-access")
    public String user() {
        return "Hello, User!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin-access")
    public String admin() {
        return "Hello, Admin!";
    }

    @PreAuthorize("hasRole('VERIFIER')")
    @GetMapping("/verifier-access")
    public String verify() {
        return "Hello, Quality Verifier!";
    }

}
