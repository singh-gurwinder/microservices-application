package com.sidhu.user.controller;

import com.sidhu.user.entity.User;
import com.sidhu.user.service.UserService;
import com.sidhu.user.vo.AccountVO;
import com.sidhu.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}/accounts")
    public UserVO getUserAccounts(@PathVariable("id") final Long id) {
        log.info("UserController: Get accounts for  User Id: " + id);
        return userService.getUserAccounts(id);
    }

    @GetMapping
    public List<User> allUsers() {
        log.info("UserController: Get all users.");
        return userService.getAllUsers();
    }

    @PostMapping
    public User save(@RequestBody User user) {
        log.info("UserController: Save user.");
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public User userById(@PathVariable("id") final Long id) {
        log.info("UserController: Get user by id: " + id);
        return userService.getUserById(id);
    }
}
