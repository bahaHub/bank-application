package com.baha.bankapp.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.simple.JSONValue;

import com.baha.bankapp.common.StringUtil;
import com.baha.bankapp.common.CommonConstant;

/**
 * @author Shin
 *
 */
public class BaseValidator {
	protected Map<String, Object> getMap(Object value) {
		try {
			Map<String, Object> result = (Map<String, Object>) JSONValue.parse(String.valueOf(value));
			if (value == null || result == null) {
				return new HashMap<String, Object>();
			}
			return result;
		} catch (Exception e) {
			return new HashMap<String, Object>();
		}
	}

	protected List<Map<String, Object>> getList(Object value) {
		try {
			List<Map<String, Object>> result = (List<Map<String, Object>>) JSONValue.parse(String.valueOf(value));
			if (value == null || result == null) {
				return new ArrayList<Map<String, Object>>();
			}
			return result;
		} catch (Exception e) {
			return new ArrayList<Map<String, Object>>();
		}
	}

	protected boolean isInvalid(String value, boolean isMandatory, int minLeng, int maxLeng, boolean isNum, boolean isYesOrNo,
			boolean isValidDate) {
		try {
			String target = value;
			if (isMandatory && StringUtil.isNullorBlank(value)) {
				return true;
			}

			if (!isMandatory && StringUtil.isNullorBlank(value)) {
				return false;
			}

			target = target == null ? "" : target;
			if (minLeng > 0 && value.length() < minLeng) {
				return true;
			}

			if (maxLeng > 0 && value.length() > maxLeng) {
				return true;
			}

			Pattern pattern = Pattern.compile("[0-9]*");
			if (isNum && !pattern.matcher(target).matches()) {
				return true;
			}

			if (isYesOrNo && !value.equals(CommonConstant.TRUE) && !value.equals(CommonConstant.FALSE)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	protected boolean isEmpty(String value) {
		return isInvalid(value, true, 0, 0, false, false, false);
	}

	protected boolean isNotDigit(String value) {
		return isInvalid(value, false, 0, 0, true, false, false);
	}

	protected boolean isNotDigit(String value, int leng) {
		return isInvalid(value, false, leng, leng, true, false, false);
	}

	protected boolean isNotDigit(String value, int minLeng, int maxLeng) {
		return isInvalid(value, false, minLeng, maxLeng, true, false, false);
	}

	protected boolean isLess(String value, int minLeng) {
		return isInvalid(value, false, minLeng, 0, false, false, false);
	}

	protected boolean isOver(String value, int maxLeng) {
		return isInvalid(value, false, 0, maxLeng, false, false, false);
	}

	protected boolean isInvalidLeng(String value, int leng) {
		return isInvalid(value, false, leng, leng, false, false, false);
	}

	protected boolean isInvalidLeng(String value, int minLeng, int maxLeng) {
		return isInvalid(value, false, minLeng, maxLeng, false, false, false);
	}

	protected boolean isNotYesOrNo(String value) {
		return isInvalid(value, false, 0, 0, false, true, false);
	}

	protected boolean isNotValidDate(String value) {
		return isInvalid(value, false, 0, 0, false, false, true);
	}

}