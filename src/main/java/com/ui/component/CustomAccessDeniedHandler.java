package com.ui.component;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response,
//                       AccessDeniedException accessDeniedException) throws IOException, ServletException
//    {
//        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        response.setContentType("text/html;charset=UTF-8");
//        response.getWriter().write("<h1>403 - Access Denied</h1><p>You do not have permission to access this resource.</p>");
//    }


    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<h1>403 - Access Denied</h1><p>You do not have permission to access this resource.</p>");
        response.getWriter().write("<a href=\"/login\" >LOGIN</a>");
        response.getWriter().write("<br><a href=\"/login-form-vm\" >CREATE AN ACCOUNT</a>");
    }
}
