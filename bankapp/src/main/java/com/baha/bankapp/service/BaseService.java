package com.baha.bankapp.service;

import java.util.HashMap;
import java.util.Map;

import com.baha.bankapp.common.CommonConstant;

public class BaseService {
	
	public Map<String, Object> setReturnMap(String returnCode, String returnMessage) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(CommonConstant.STR_RETURN_CODE, returnCode);
		returnMap.put(CommonConstant.STR_RETURN_MESSAGE, returnMessage);
		
		return returnMap;
	}

}
