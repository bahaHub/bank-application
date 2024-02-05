package com.baha.bankapp.validator;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.baha.bankapp.common.BaseValidator;
import com.baha.bankapp.model.Customer;


public class CustomerValidator extends BaseValidator implements Validator {
	public boolean supports(Class clazz) {
		return Customer.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		if (target instanceof Customer) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "custNo", "The custNo is mandatory field.");
		} else {
			errors.rejectValue("validateFailed", "No match DTO");
		}
	}

	public String getErrorsMsg(BindingResult result) {
		String msg = "";
		List errorList = result.getFieldErrors();
		for (int i = 0; i < errorList.size(); i++) {
			FieldError error = (FieldError) errorList.get(i);
			msg += " Filed : " + error.getField() + ", Field Value : " + error.getDefaultMessage() + ", " + ", ErrorMsg : "
					+ error.getCode() + ", ";
		}
		return msg;
	}
}