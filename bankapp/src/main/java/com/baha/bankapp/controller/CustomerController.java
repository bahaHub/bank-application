package com.baha.bankapp.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baha.bankapp.model.Customer;
import com.baha.bankapp.service.CustomerServiceManager;

@RestController
@CrossOrigin("http://localhost:3000")
public class CustomerController {
	
	@Autowired
	private CustomerServiceManager customerServiceManager;
	
	@PostMapping("/register")
	ResponseEntity<Map<String, Object>> createCustomer(@RequestBody Customer newCust) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap = customerServiceManager.createCustomer(newCust);
		
		return ResponseEntity.ok().body(returnMap);
	}
	
	@GetMapping("/users")
	ResponseEntity<Map<String, Object>> getCustomerList() {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap = customerServiceManager.getCustomerList();
		
		return ResponseEntity.ok().body(returnMap);
	}
	
	@GetMapping("/user/{custNo}")
	ResponseEntity<Map<String, Object>> getCustomerDetail(@PathVariable String custNo) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap = customerServiceManager.getCustomerDetail(custNo);
		
		return ResponseEntity.ok().body(returnMap);
	}
}
