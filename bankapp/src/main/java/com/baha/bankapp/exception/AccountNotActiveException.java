package com.baha.bankapp.exception;

import com.baha.bankapp.common.CommonConstant;

public class AccountNotActiveException extends RuntimeException {
	
	private static final long serialVersionUID = -603594359529978250L;

	public AccountNotActiveException(String accNo) {
		super(CommonConstant.STR_RETURN_MESSAGE_20002 + " : " + accNo);
	}
	
}