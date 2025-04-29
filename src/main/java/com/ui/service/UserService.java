package com.ui.service;

import com.ui.mapper.OrderRowMapper;
import com.ui.mapper.UserRowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ui.model.User;
import com.ui.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class UserService {

    Logger logger= LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository,
                        JdbcTemplate jdbcTemplate){
        this.userRepository= userRepository;
        this.jdbcTemplate= jdbcTemplate;
    }

    private UserRepository userRepository;
    private JdbcTemplate jdbcTemplate;

    public User save(User user){

        User saved= this.userRepository.save(user);
        logger.info("Saved new user ok: "+ saved.getName()+ "   "+ saved.getEmail());
        return saved;
    }

    public User findByUserName(String name) {

        String sql = "SELECT * FROM USERS WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new UserRowMapper());
    }

    public User findByUserEmail(String email) {

        String sql = "SELECT * FROM USERS WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserRowMapper());
    }

    public void deleteByName(User user) {
        String sql = "UPDATE USERS SET editable = false WHERE name = ?";
        jdbcTemplate.update(sql, user.getName());
        logger.info(" deleted user:"+ user.getName()+ "   "+ user.getEmail());
    }

    public void deleteByEmail(User user) {
        String sql = "UPDATE USERS SET editable = false WHERE email = ?";
        jdbcTemplate.update(sql, user.getEmail());
        logger.info("deleted user:"+ user.getName()+ "   "+ user.getEmail());
    }

    public void updatedPassword(User user, String newPassword, PasswordEncoder encoder) {
        String sql = "UPDATE USERS SET password = ? WHERE email = ?";
        jdbcTemplate.update(sql, encoder.encode(newPassword), user.getEmail());
        logger.info("updated password for user:"+ user.getName()+ "   "+ user.getEmail());
    }

}
