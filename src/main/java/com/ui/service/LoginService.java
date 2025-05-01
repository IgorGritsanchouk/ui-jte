package com.ui.service;

import com.ui.model.User;
import com.ui.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginService {

    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public LoginService(UserRepository userRepository,
                        JdbcTemplate jdbcTemplate){

        this.userRepository= userRepository;
        this.jdbcTemplate= jdbcTemplate;
    }

    private UserRepository userRepository;

    private JdbcTemplate jdbcTemplate;


    public boolean login(String username, String password){

        return true;
    }

}
