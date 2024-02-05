package com.baha.bankapp.service;

import java.util.Map;

import com.baha.bankapp.dto.AccountDto;
import com.baha.bankapp.model.Account;

public interface AccountServiceManager {

	public Map<String, Object> createAccount(Account newAccount, String custNo);

	public Map<String, Object> inquireCustomerAccountList(String custNo);
	
	public Map<String, Object> depositAmount(AccountDto accountDto, String accNo);
	
	public Map<String, Object> withdrawAmount(AccountDto accountDto, String accNo);
	
	public Map<String, Object> closeAccount(String accNo);
}
