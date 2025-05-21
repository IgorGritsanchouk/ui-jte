package com.ui.service;

import com.ui.mapper.UserRowMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ui.model.User;
import com.ui.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public String getGreeting(String id){

        Optional<User> user= userRepository.findById(id);
        return "Hello, " + user.get().getUserName() + "!";
    }

    @Caching(put = {
            @CachePut(cacheNames = "cacheUsers", key = "#user.userName"),
            @CachePut(cacheNames = "cacheUsers", key = "#user.email")
    })
    public User save(User user){

        User saved= null;

        if (!userRepository.existsById(user.getUserName())){ // will update

            saved=createNewUser(user);   //  insert
        } else {
            saved= userRepository.save(user); //update
        }

        logger.info("Saved new user ok: "+ saved.getUserName()+ "   "+ saved.getEmail());
        return saved;
    }

    @Caching(put = {
            @CachePut(cacheNames = "cacheUsers", key = "#user.userName"),
            @CachePut(cacheNames = "cacheUsers", key = "#user.email")
    })
    public User saveNewUser(String userName, String email){

        User saved= null;

        User newUser= new User();
        newUser.setUserName(userName);
        newUser.setEmail(email);
        newUser.setPassword(encodePassword(userName+"_password"));
        newUser.setEnabled(true);

        if (!userRepository.existsById(newUser.getUserName())){ // will update

            saved=createNewUser(newUser);   //  insert
        } else {
            saved= userRepository.save(newUser); //update
        }

        logger.info("Saved new user ok: "+ saved.getUserName()+ "   "+ saved.getEmail());
        return saved;
    }

    @Cacheable(cacheNames = "cacheUsers", key = "#userName")
    public User findByUserName(String userName) {

        String sql = "SELECT * FROM USERS WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userName}, new UserRowMapper());
    }

    @Cacheable(cacheNames = "cacheUsers", key = "#email")
    public User findByUserEmail(String email) {

        String sql = "SELECT * FROM USERS WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserRowMapper());
    }

    @Cacheable(cacheNames = "cacheUsers", key = "#user.email")
    public User findByUserEmail(User user) {

        String sql = "SELECT * FROM USERS WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{user.getEmail()}, new UserRowMapper());
    }

    @CacheEvict(cacheNames = "cacheUsers", key = "userName")
    public void deleteByName(String userName) {

        String sql = "UPDATE USERS SET editable = false WHERE username = ?";
        jdbcTemplate.update(sql, userName);
        logger.info(" deleted user:"+ userName);
    }

    @CacheEvict(cacheNames = "cacheUsers", key = "email")
    public void deleteByEmail(String email) {

        String sql = "UPDATE USERS SET editable = false WHERE email = ?";
        int updated= jdbcTemplate.update(sql, email);
        logger.info("deleted user:"+ updated + "   " + email);
    }

    @Transactional
    public User createNewUser(User user) {
        String encodedPassword = encodePassword(user.getPassword());

        String sql1 = "INSERT INTO users (username, email, password, enabled) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql1, user.getUserName(), user.getEmail(), encodedPassword, true);

        // Update the user object to reflect stored values
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        logger.info("Inserted new user: " + user.getUserName() + " | " + user.getEmail());

        String sql2 = "INSERT INTO authorities (username, authority) VALUES (?, ?)";
        jdbcTemplate.update(sql2, user.getUserName(), "ROLE_USER");
        logger.info("Inserted ROLE_USER - default role for user: " + user.getUserName());

        return user;
    }

    public void updatedPassword(User user, String newPassword, PasswordEncoder encoder) {

        String sql = "UPDATE USERS SET password = ? WHERE email = ?";
        jdbcTemplate.update(sql, encoder.encode(newPassword), user.getEmail());
        logger.info("updated password for user:"+ user.getUserName()+ "   "+ user.getEmail());
    }

    public String encodePassword(String notEncodedPassword){

        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        return encoder.encode(notEncodedPassword);
    }

}
