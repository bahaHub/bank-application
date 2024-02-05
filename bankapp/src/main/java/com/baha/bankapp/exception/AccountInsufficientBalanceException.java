package com.baha.bankapp.exception;

import com.baha.bankapp.common.CommonConstant;

public class AccountInsufficientBalanceException extends RuntimeException {
	
	private static final long serialVersionUID = -7744680082286556426L;

	public AccountInsufficientBalanceException(String accNo) {
		super(CommonConstant.STR_RETURN_MESSAGE_20003 + " Account Number : " + accNo);
	}
	
}