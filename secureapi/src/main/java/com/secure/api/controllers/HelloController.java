package com.secure.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello(){
        return "hello from hello controller.";
    }

    @GetMapping("/account")
    public String account(){
        return "My account.";
    }

    @GetMapping("/balance")
    public String balance(){
        return "Balance.";
    }

    @GetMapping("/loan")
    public String loan(){
        return "Loan.";
    }
}
