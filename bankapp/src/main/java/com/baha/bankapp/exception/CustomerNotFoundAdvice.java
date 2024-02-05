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
public class CustomerNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(CustomerNotFoundException.class)
	public Map<String, String> exceptionHandler(CustomerNotFoundException exception) {
		Map<String,String> errorMap = new HashMap<>();
		errorMap.put(CommonConstant.STR_RETURN_CODE, CommonConstant.RETURN_CODE_10001);
		errorMap.put(CommonConstant.STR_RETURN_MESSAGE, exception.getMessage());
		
		return errorMap;
	}

}
