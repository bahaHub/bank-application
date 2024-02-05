package com.baha.bankapp.exception;

import com.baha.bankapp.common.CommonConstant;

public class AccountNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -4590051861511984086L;

	public AccountNotFoundException(String accNo) {
		super(CommonConstant.STR_RETURN_MESSAGE_20001 + " : " + accNo);
	}
	
}