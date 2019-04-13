package com.airwallex.tools.calculator;

import com.airwallex.tools.calculator.util.NumberUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class NumberUtilTest extends TestCase {
	
	public NumberUtilTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(NumberUtilTest.class);
	}

	public void testIsNum() {
		assertTrue(NumberUtil.isNum("21321321"));
		assertTrue(NumberUtil.isNum("-354543"));
		assertTrue(NumberUtil.isNum("-3545.43"));
		assertTrue(NumberUtil.isNum("21321.321"));

		assertFalse(NumberUtil.isNum("a21321.321"));
		assertFalse(NumberUtil.isNum("2sasads"));
	}
}
