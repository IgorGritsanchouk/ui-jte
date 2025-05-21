package com.ui.service;

import com.ui.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ui.model.User;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetGreeting() {

        User user= new User();
        user.setUserName("Bob");
        Optional<User> optionalUser= Optional.of(user);

        when(userRepository.findById(1L+"")).thenReturn(optionalUser);

        String greeting = userService.getGreeting(1L+"");
        assertEquals("Hello, Bob!", greeting);

        verify(userRepository).findById(1L+"");
    }

   @Test
    void testGetGreetingNull() {

        User usr= new User();
        //Optional<User> emptyUser = Optional.empty();
        Optional<User> userOpt= Optional.ofNullable(usr);
        userOpt.ifPresent(user -> System.out.println("Found: " + user.getUserName()));

        when(userRepository.findById(2L+"")).thenReturn(userOpt);

        String greeting = userService.getGreeting(2L+"");
        assertEquals("Hello, null!", greeting);

        verify(userRepository).findById(2L+"");
    }

    //Summary
    //Use @Mock to create fake dependencies.
    //Use @InjectMocks to inject those into the class under test.
    //Use when(...).thenReturn(...) to define mock behavior.
    //Use verify(...) to ensure interactions happened.

}
