package com.airwallex.tools.calculator.util;

import java.util.regex.Pattern;

public class NumberUtil {

	public static boolean isNum(String str) {
		// Check whether is integer
		boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(str).find();
		// Check whether is double
		boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(str).find();
		return isInt || isDouble;
	}
}
