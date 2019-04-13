package com.airwallex.tools.calculator;

import java.math.BigDecimal;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.OperatorResult.Status;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for RPNCalculator.
 */
public class RPNCalculatorTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public RPNCalculatorTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(RPNCalculatorTest.class);
	}

	/**
	 * RPNCalculator Test
	 */
	public void testSimpleMulOperator() {
		String expression = "5 6 * 4 8 *";
		RPNCalculator calculator = new RPNCalculator(100);
		OperatorResult result = calculator.exec(expression);
		
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal(30)));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal(32)));
	}
	
	/**
	 * RPNCalculator Test
	 */
	public void testNumberAccept() {
		String expression = "5 2";
		RPNCalculator calculator = new RPNCalculator(100);
		OperatorResult result = calculator.exec(expression);
		
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal(5)));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal(2)));
	}
}
