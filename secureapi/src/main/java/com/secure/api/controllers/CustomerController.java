package com.secure.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secure.api.models.Customer;
import com.secure.api.services.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;
	private final PasswordEncoder passwordEncoder;
	   
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
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer) {
    	String pasword = passwordEncoder.encode(customer.getPasswrd()).toString();
    	customer.setPasswrd(pasword);
    	
    	customerService.save(customer);
    	
    	return ResponseEntity.status(HttpStatus.CREATED)
    				.body("User created successfully.");
    }
}
