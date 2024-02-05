package com.baha.bankapp.service;

import java.util.Map;

import com.baha.bankapp.model.Customer;

public interface CustomerServiceManager {
	
	public static final String LOOKUP_NAME = "com.baha.bankapp.service.CustomerServiceManager";
	
	public Map<String, Object> createCustomer(Customer newCust);

	public Map<String, Object> getCustomerList();
	
	public Map<String, Object> getCustomerDetail(String custNo);
}
