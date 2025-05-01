package com.ui.controllers;

import com.ui.model.User;
import com.ui.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    public UserRestController(UserService userService) {

        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping("/create-user")
    //@GetMapping("/{username}/{email}")
    public User createUser(@RequestParam("userName") String userName,
                             @RequestParam("email") String email) {
    //public User generateUser(@PathVariable(value = "userName") String userName,
    //                         @PathVariable(value = "email") String email) {
    //http://localhost/create-user?userName=MT021&email=martine.courtois@gmail.com

        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(userService.encodePassword(userName + "_password"));
        user.setEnabled(true);

        return userService.save(user);
    }

    @GetMapping("/user-name/{userName}")
    //public User getUserByName(@RequestParam("userName") String userName){
    public User getUserByName(@PathVariable(value = "userName") String userName) {

        return userService.findByUserName(userName);
    }

    @GetMapping("/user-email")
    //@GetMapping("/{email}")
    public User getUserByEmail(@RequestParam("email") String email){
    //public User getUserByEmail(@PathVariable(value = "email") String email) {
        //http://localhost/user-email?email=user@gmail.com

        return userService.findByUserEmail(email);
    }

    @GetMapping("/delete/{email}")
    //public void deleteUserByName(@PathVariable(name = "email") String email)
    public void deleteUserByName(@PathVariable(value = "email") String email) {

        userService.deleteByEmail(email);
    }

    //@PatchMapping

}
