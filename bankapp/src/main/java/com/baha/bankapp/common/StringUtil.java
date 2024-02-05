package com.baha.bankapp.common;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Library of utility functions for the manipulation of strings.
 *
 * @version 1.0
 * @author Paulo Villela
 */
public abstract class StringUtil {

	/**
	 * Constant for default mail character set to send mail in Korean
	 */
	private final static String CHAR_SET_KOR = "euc-kr";

	/**
	 * Constant for mail character set to send mail in iso-8859-1
	 */
	private final static String CHAR_SET_WESTERN_EUROPEAN = "latin1";

	/**
	 * Takes a <code>String</code> representing a Unix or URL path and returns
	 * it minus the right-most path component (using '/' as a delimiter).
	 *
	 * @param path A string representing a Unix or URL path.
	 * @return The truncated path.
	 */
	public static String truncatePathAtRight(String path) {
		int index = path.lastIndexOf('/');

		if (index == -1) {
			return path;
		}

		return path.substring(0, index);
	}

	/**
	 * Takes a <code>String</code> representing a Unix or URL path and returns
	 * the right-most path component (using '/' as a delimiter). If there is no
	 * '/', the original path is returned.
	 *
	 * @param path A <code>String</code> representing a Unix or URL path.
	 *
	 * @return The right-most path component.
	 */
	public static String getRightMostPartOfPath(String path) //
	// Returns the substring from the last slash to the end. If there
	// is no slash, returns the original string.
	{
		int index = path.lastIndexOf('/');

		if (index == -1) {
			return path;
		}

		return path.substring(index);
	}

	/**
	 * Takes a <code>String</code> representing a Unix or URL path and returns
	 * it minus the ending slash ('/').
	 *
	 * @param path A <code>String</code> representing a Unix or URL path.
	 *
	 * @return The path minus the ending slash ('/').
	 */
	public static String trimEndingSlash(String path) {
		if (path.endsWith("/")) {
			return path.substring(0, path.length() - 1);
		} else {
			return path;
		}
	}

	/**
	 * Takes a <code>String</code> representing a Unix or URL path and returns
	 * it minus the beginning slash ('/').
	 *
	 * @param path A <code>String</code> representing a Unix or URL path.
	 *
	 * @return The path minus the beginning slash ('/').
	 */
	public static String trimBeginningSlash(String path) {
		if (path.startsWith("/")) {
			return path.substring(1);
		} else {
			return path;
		}
	}

	/**
	 * Ensures a non-null <code>String</code>. If the provided
	 * <code>String</code> is not null, the original <code>String</code> is
	 * returned. If the provided <code>String</code> is null, an empty
	 * <code>String</code> is returned.
	 *
	 * @param checkString The <code>String</code> to evaluate.
	 *
	 * @return The original <code>String</code> if it is not null or an empty
	 * <code>String</code> if the original is null.
	 *
	 */
	public static String nonNullString(String checkString) {
		if (checkString != null) {
			return checkString;
		} else {
			return "";
		}
	}

	/**
	 * Populates a <code>List</code> with the components of a provided
	 * <code>String</code> delimited by the parameter
	 * <code>separatorToken</code>.
	 *
	 * @param sourceString The <code>String</code> whose contents will
	 * populate a <code>List</code>.
	 * @param separatorToken The delimiter used to parse the <code>String</code>.
	 *
	 * @return A <code>List</code> populated with a <code>String</code>'s
	 * components delimited by the <code>separatorToken</code>.
	 */
	public static List stringToList(String sourceString, String separatorToken) {
		List destinationList = new ArrayList();
		int index = -1;

		while (true) {
			int oldIndex = index + 1;
			index = sourceString.indexOf(separatorToken, oldIndex);

			if (index != -1) {
				destinationList.add(sourceString.substring(oldIndex, index));
			} else {
				destinationList.add(sourceString.substring(oldIndex, sourceString.length()));

				break;
			}
		}

		return destinationList;
	}

	/**
	 * Populate a map with key and value from a list of key-value pair. Each
	 * key-value pair is delimited by <code>=</code>. The list of key-value
	 * pair is delimited by the parameter <code>
	 * separatorToken</code>. The
	 * key is converted to uppercase prior to storing in the map.
	 *
	 * Eg. "key1=MyValue1 key100=SomeValue2 KEY2=AnotherrValue2" will translate
	 * into [[KEY1=MyValue1], [KEY100=SomeValue2], [KEY2=AnotherrValue2]], a map
	 * with 3 entry.
	 *
	 * @param sourceString argument line.
	 * @param separatorToken the token to separate the argument.
	 * @return a map for the source string.
	 */
	public static Map stringKeyValuePairToMap(String sourceString, String separatorToken) {
		Map destinationMap = new HashMap();
		int index = -1;

		do {
			int oldIndex = index + 1;
			index = sourceString.indexOf(separatorToken, oldIndex);

			String keyValuePair = "";

			if (index != -1) {
				keyValuePair = sourceString.substring(oldIndex, index);
			} else {
				keyValuePair = sourceString.substring(oldIndex, sourceString.length());
			}

			String[] tmpString = keyValuePair.split("=");

			if (tmpString.length == 2) {
				destinationMap.put(tmpString[0].toLowerCase(), tmpString[1]);
			}
		} while (index != -1);

		return destinationMap;
	}

	public static Map stringKeyValuePairArrayToMap(String[] keyValuePairs, String separatorToken) {
		Map destinationMap = new HashMap();

		for (int i = 0; i < keyValuePairs.length; i++) {
			String[] tmpString = keyValuePairs[i].split("=");

			if (tmpString.length == 2) {
				destinationMap.put(tmpString[0].toLowerCase(), tmpString[1]);
			}
		}

		return destinationMap;
	}

	/**
	 * Builds a <code>String</code> with the components of a provided
	 * <code>List</code> delimited by the parameter
	 * <code>separatorToken</code>.
	 *
	 * @param lst The <code>List</code> whose contents will populate a
	 * <code>String</code>.
	 * @param separatorToken The delimiter used to parse the <code>String</code>.
	 *
	 * @return A <code>String</code> populated with a <code>List</code>'s
	 * components delimited by the <code>separatorToken</code>.
	 */
	public static String listToString(List lst, String separatorToken) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < lst.size(); i++) {
			if (i != 0) {
				buf.append(separatorToken);
				buf.append(lst.get(i));
			} else {
				buf.append(lst.get(i));
			}
		}

		return buf.toString();
	}

	/**
	 * Returns the classname of the provided class, minus the package name.
	 *
	 * @param sourceClass The class used for the classname
	 * @return The classname of the provided class, minus the package name.
	 */
	public static String getShortClassName(Class sourceClass) {
		String longName = sourceClass.getName();
		int lastDotIndex = longName.lastIndexOf('.');

		return longName.substring(lastDotIndex + 1);
	}

	/**
	 * Static method substituting the placeholders defined by the
	 * argumentDefination variable in a source string with the values passed in
	 * the array of String values.
	 * @param sourceString source string containing the placeholders.
	 * @param argumentDefination placeholder character in the source string to
	 * be replaced.
	 * @param arguments Array of Strings to be replaced in place of the
	 * placeholders.
	 * @return String.
	 */
	public static String stringSubstituteArgs(String sourceString, String argumentDefination, String[] arguments) {
		assert ((sourceString != null) && (argumentDefination != null) && (arguments != null)) : "";

		StringBuffer appendBuffer = new StringBuffer(sourceString.length() + (16 * arguments.length));

		// Allocate enough space to hopefully avoid resizing
		int startIndex = 0;

		for (int i = 0; i < arguments.length; ++i) {
			int index = sourceString.indexOf(argumentDefination, startIndex);
			assert (index >= 0) : "Error: Util.StringSubstituteArgs - " + "more arguments than slots";

			appendBuffer.append(sourceString.substring(startIndex, index));
			appendBuffer.append(arguments[i]);

			startIndex = index + argumentDefination.length();
		}

		appendBuffer.append(sourceString.substring(startIndex));

		return appendBuffer.toString();
	}

	/**
	 * Static method converting an Array of Strings to a String object.
	 * @param sourceArray Array of Strings.
	 * @return String.
	 */
	public static String stringArrayToString(String[] sourceArray) {
		StringBuffer appendBuffer = new StringBuffer(200);

		for (int i = 0; i < sourceArray.length; ++i) {
			if (i != 0) {
				appendBuffer.append(", ");
			}

			appendBuffer.append(sourceArray[i]);
		}

		return appendBuffer.toString();
	}

	/**
	 * Static method which returns the number of occurences of a character in a
	 * String.
	 * @param checkString Source string in which the occurences of the character
	 * has to be ascertained.
	 * @param characterSearch character whose occurence has to be ascertained.
	 * @return int.
	 */
	public static int getCharCount(String checkString, char characterSearch) {
		char[] charTarget = { characterSearch };

		return StringUtils.countMatches(checkString, new String(charTarget));
	}

	/**
	 * @param object String object to check
	 * @return boolean true when object is null or blank
	 */
	public static boolean isNullorBlank(String object) {
		return (object == null) || "".equals(object.trim());
	}

	/**
	 * Helper method that use the reflection to return the string representation
	 * of the object.
	 *
	 * @param obj Object to return the string representation of.
	 * @return String representation of the object or null if object passed is
	 * null
	 */
	public static String toString(Object obj) {
		/**
		 * create ReflectionToStringBuilder pass on this object reference and
		 * null for both style and buffer. If the style is null, the default
		 * style is used. If the buffer is null, a new one is created.
		 */
		String result = null;

		if (obj != null) {
			ReflectionToStringBuilder stringBuilder = new ReflectionToStringBuilder(obj, null, null);
			result = stringBuilder.toString();
		}

		return result;
	}

	/**
	 * Returns Korean Expression
	 * @param expression String contains Korean value
	 * @return String returned string in Korean character set
	 * @throws UnsupportedEncodingException
	 */
	public static String getKoreanString(String expression) throws UnsupportedEncodingException {
		return new String(expression.getBytes(CHAR_SET_KOR), CHAR_SET_WESTERN_EUROPEAN);
	}

	/**
	 * Return HexString from Byte Array Data.
	 * @param datas byte array data
	 * @return HexString
	 */
	public static String getHexStringFromBytes(byte[] datas) {
		StringBuffer buffer = new StringBuffer(datas.length * 2);
		for (int i = 0; i < datas.length; i++) {
			buffer.append(Integer.toHexString(0xFF & datas[i]));
		}
		return buffer.toString();
	}

	/**
	 * Replace all occurences of a substring within a string with another
	 * string.
	 * @param inString String to examine
	 * @param oldPattern String to replace
	 * @param newPattern String to insert
	 * @return a String with the replacements
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		// remember to append any characters to the right of a match
		return sbuf.toString();
	}
}