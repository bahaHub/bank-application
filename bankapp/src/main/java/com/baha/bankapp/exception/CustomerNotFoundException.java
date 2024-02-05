package com.baha.bankapp.exception;

public class CustomerNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 7882245265665276872L;

	public CustomerNotFoundException(String id) {
		super("Could not find the customer id " + id);
	}
	
}
