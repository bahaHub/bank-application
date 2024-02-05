package com.baha.bankapp.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

	public static final String CUSTOMER_NO_PREFIX = "00";
	public static final String CUSTOMER_NO_FORMAT = "yyMMddHHmmssSS";
	public static final Integer ACCOUNT_ACTIVE = 1;
	public static final Integer ACCOUNT_INACTIVE = 0;
	public static final String STR_SYSTEM = "SYSTEM";
	
	public static final String STR_CODE = "CODE";
	public static final String STR_MESSAGE = "MESSAGE";
	public static final String STR_SUCCESS = "SUCCESS";
	public static final String STR_FAILED = "FAILED";
	
	public static final String generateAccountNumber() {
	    SimpleDateFormat sdf = new SimpleDateFormat(CUSTOMER_NO_FORMAT);
	    return String.format("%s%s", CUSTOMER_NO_PREFIX, sdf.format(new Date()));
	}
}
