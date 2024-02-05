package com.baha.bankapp.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baha.bankapp.common.CommonConstant;
import com.baha.bankapp.common.CommonUtils;
import com.baha.bankapp.common.StringUtil;
import com.baha.bankapp.exception.CustomerNotFoundException;
import com.baha.bankapp.model.Customer;
import com.baha.bankapp.repository.CustomerRepository;
import com.baha.bankapp.service.BaseService;
import com.baha.bankapp.service.CustomerServiceManager;

@Service
public class CustomerServiceManagerImpl extends BaseService  implements CustomerServiceManager {

	@Autowired
	private CustomerRepository customerRepository = null;
	
	@Override
	public Map<String, Object> createCustomer(Customer newCust) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (StringUtil.isNullorBlank(newCust.getCustName())) {
			returnMap = setReturnMap(CommonConstant.RETURN_CODE_10001, CommonConstant.STR_RETURN_MESSAGE_10001);
			return returnMap;
		}
		
		newCust.setCreatedBy(CommonUtils.STR_SYSTEM);
		newCust.setCreatedDate(LocalDateTime.now());
		newCust.setUpdatedBy(CommonUtils.STR_SYSTEM);
		newCust.setUpdatedDate(LocalDateTime.now());
		
		newCust = customerRepository.save(newCust);
		
		returnMap = setReturnMap(CommonConstant.RETURN_CODE_SUCCESS, CommonConstant.RETURN_MESSAGE_SUCCESS);
		returnMap.put(CommonConstant.STR_OBJECT, newCust);
		
		return returnMap;
		
	}
	
	@Override
	public Map<String, Object> getCustomerList() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<Customer> list = customerRepository.findAll();
		
		returnMap = setReturnMap(CommonConstant.RETURN_CODE_SUCCESS, CommonConstant.RETURN_MESSAGE_SUCCESS);
		returnMap.put(CommonConstant.STR_OBJECT, list);
		
		return returnMap;
		
	}

	@Override
	public Map<String, Object> getCustomerDetail(String custNo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (StringUtil.isNullorBlank(custNo)) {
			returnMap = setReturnMap(CommonConstant.RETURN_CODE_10002, CommonConstant.STR_RETURN_MESSAGE_10002);
			return returnMap;
		}

		List<Customer> cust = customerRepository.findByCustNo(custNo);
		
		if (cust.isEmpty()) {
			throw new CustomerNotFoundException(custNo);
		}
		
		returnMap = setReturnMap(CommonConstant.RETURN_CODE_SUCCESS, CommonConstant.RETURN_MESSAGE_SUCCESS);
		returnMap.put(CommonConstant.STR_OBJECT, cust);
		
		return returnMap;
	}
	
}
