package com.baha.bankapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.baha.bankapp.common.CommonConstant;

@ControllerAdvice
public class AccountNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(AccountNotFoundException.class)
	public Map<String, String> exceptionHandler(AccountNotFoundException exception) {
		Map<String,String> errorMap = new HashMap<>();
		errorMap.put(CommonConstant.STR_RETURN_CODE, CommonConstant.RETURN_CODE_20001);
		errorMap.put(CommonConstant.STR_RETURN_MESSAGE, exception.getMessage());
		
		return errorMap;
	}

}
