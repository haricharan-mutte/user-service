package com.example.user_service.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("call from orderService");
        return "Hello from User Service!";
    }
    @GetMapping("/user")
    public String forUser() {
        return "Hello USER, here are your orders.";
    }

    @GetMapping("/admin")
    public String forAdmin() {
        return "Hello ADMIN, here are all orders.";
    }
}

