package com.baha.bankapp.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baha.bankapp.dto.AccountDto;
import com.baha.bankapp.model.Account;
import com.baha.bankapp.service.AccountServiceManager;

@RestController
@CrossOrigin("http://localhost:3000")
public class AccountController {
	
	@Autowired
	private AccountServiceManager accountServiceManager;
	
	@PostMapping("/account/create/{custNo}")
	ResponseEntity<Map<String, Object>> createAccount(@RequestBody Account newAccount, @PathVariable String custNo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		returnMap = accountServiceManager.createAccount(newAccount, custNo);
		
		return ResponseEntity.ok().body(returnMap);
	}
	
	@GetMapping("/account/list/{custNo}")
	ResponseEntity<Map<String, Object>> inquireCustomerAccountList(@PathVariable String custNo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		returnMap = accountServiceManager.inquireCustomerAccountList(custNo);
		
		return ResponseEntity.ok().body(returnMap);
	}
	
	@PutMapping("/account/deposit/{accNo}")
	ResponseEntity<Map<String, Object>> depositAmount(@RequestBody AccountDto accountDto, @PathVariable String accNo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		returnMap = accountServiceManager.depositAmount(accountDto, accNo);
		
	    return ResponseEntity.ok().body(returnMap);
	}
	
	@PutMapping("/account/withdraw/{accNo}")
	ResponseEntity<Map<String, Object>> withdrawAmount(@RequestBody AccountDto accountDto, @PathVariable String accNo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		returnMap = accountServiceManager.withdrawAmount(accountDto, accNo);
		
	    return ResponseEntity.ok().body(returnMap);
	}
	
	@DeleteMapping("/account/deactivate/{accNo}")
	ResponseEntity<Map<String, Object>> closeAccount(@PathVariable String accNo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		System.out.println(accNo);
		
		returnMap = accountServiceManager.closeAccount(accNo);
			    
	    return ResponseEntity.ok().body(returnMap);
	}
	
}
