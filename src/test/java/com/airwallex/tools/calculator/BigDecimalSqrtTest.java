package com.airwallex.tools.calculator;

import java.math.BigDecimal;

import com.airwallex.tools.calculator.util.BigDecimalSqrt;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BigDecimalSqrtTest extends TestCase {
	
	public BigDecimalSqrtTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(BigDecimalSqrtTest.class);
	}

	public void testSqrt() {
		BigDecimal num = new BigDecimal("2");
		BigDecimal root = BigDecimalSqrt.sqrt(num, 15);
		assertTrue(root.equals(new BigDecimal("1.414213562373095")));
		
		num = new BigDecimal("4");
		root = BigDecimalSqrt.sqrt(num, 15);
		assertTrue(root.equals(new BigDecimal("2")));
		
		num = new BigDecimal("2.56");
		root = BigDecimalSqrt.sqrt(num, 15);
		assertTrue(root.equals(new BigDecimal("1.6")));
		
		num = new BigDecimal("0");
		root = BigDecimalSqrt.sqrt(num, 15);
		assertTrue(root.equals(new BigDecimal("0")));
		
	}
}
