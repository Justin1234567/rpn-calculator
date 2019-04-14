package com.airwallex.tools.calculator;

import java.math.BigDecimal;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.OperatorResult.Status;
import com.airwallex.tools.calculator.model.enums.ErrorCode;
import com.airwallex.tools.calculator.model.enums.OperatorCommandEnum;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for RPNCalculator.
 */
public class RPNCalculatorTest extends TestCase {

	public RPNCalculatorTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(RPNCalculatorTest.class);
	}

	public void testSimpleMulOperator() {
		String expression = "5 6 * 4 8 *";
		RPNCalculator calculator = new RPNCalculator(100);
		OperatorResult result = calculator.exec(expression);
		
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal(30)));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal(32)));
	}
	
	public void testNumberAccept() {
		// Examples 1
		String expression = "5 2";
		RPNCalculator calculator = new RPNCalculator(100);
		OperatorResult result = calculator.exec(expression);
		
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal(5)));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal(2)));
	}
	
	public void testNumberSqrt() {
		// Examples 2
		String expression = "2 sqrt";
		RPNCalculator calculator = new RPNCalculator(100);
		OperatorResult result = calculator.exec(expression);
		
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("1.414213562373095")));
		
		expression = "clear 9 sqrt";
		result = calculator.exec(expression);
		
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("3")));
	}
	
	public void testNumberSub() {
		// Examples 3
		String expression = "5 2 -";
		RPNCalculator calculator = new RPNCalculator(100);
		OperatorResult result = calculator.exec(expression);
		
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("3")));
		
		expression = "3 -";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("0")));
		
		expression = "clear";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 0);
	}
	
	public void testUndo() {
		// Examples 4
		String expression = "5 4 3 2";
		RPNCalculator calculator = new RPNCalculator(100);
		OperatorResult result = calculator.exec(expression);
		
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 4);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("5")));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal("4")));
		assertTrue(result.getStack().toArray()[2].equals(new BigDecimal("3")));
		assertTrue(result.getStack().toArray()[3].equals(new BigDecimal("2")));
		
		expression = "undo undo *";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("20")));
		
		expression = "5 *";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("100")));
		
		expression = "undo";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 2);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("20")));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal("5")));
		
		expression = "undo";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("20")));
		
		expression = "undo";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 2);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("5")));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal("4")));
	}
	
	public void testDiv() {
		// Examples 5
		String expression = "7 12 2 /";
		RPNCalculator calculator = new RPNCalculator(100);
		OperatorResult result = calculator.exec(expression);
		
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 2);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("7")));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal("6")));
		
		expression = "*";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("42")));
		
		expression = "4 /";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("10.5")));
		
		expression = "undo";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 2);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("42")));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal("4")));
	}
	
	public void testMul() {
		// Examples 6
		String expression = "1 2 3 4 5";
		RPNCalculator calculator = new RPNCalculator(100);
		OperatorResult result = calculator.exec(expression);
		
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 5);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("1")));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal("2")));
		assertTrue(result.getStack().toArray()[2].equals(new BigDecimal("3")));
		assertTrue(result.getStack().toArray()[3].equals(new BigDecimal("4")));
		assertTrue(result.getStack().toArray()[4].equals(new BigDecimal("5")));
		
		expression = "*";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 4);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("1")));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal("2")));
		assertTrue(result.getStack().toArray()[2].equals(new BigDecimal("3")));
		assertTrue(result.getStack().toArray()[3].equals(new BigDecimal("20")));
		
		expression = "clear 3 4 -";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("-1")));
		
		expression = "undo";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 2);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("3")));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal("4")));
	}

	public void testMulManyTimes() {
		// Examples 7
		String expression = "1 2 3 4 5";
		RPNCalculator calculator = new RPNCalculator(100);
		OperatorResult result = calculator.exec(expression);
		
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 5);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("1")));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal("2")));
		assertTrue(result.getStack().toArray()[2].equals(new BigDecimal("3")));
		assertTrue(result.getStack().toArray()[3].equals(new BigDecimal("4")));
		assertTrue(result.getStack().toArray()[4].equals(new BigDecimal("5")));
		
		expression = "*  * * *";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("120")));
		
		expression = "undo";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 2);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("1")));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal("120")));
	}
	
	public void testException() {
		// Examples 8
		String expression = "1  2 a 4 5";
		RPNCalculator calculator = new RPNCalculator(100);
		OperatorResult result = calculator.exec(expression);
		
		assertTrue(result.getStatus() == Status.ERROR);
		assertTrue(result.getErrorCode().equals(ErrorCode.UNSUPPORTED_CMD.getCode()));
		assertTrue(result.getErrTermOffset() == 6);
		assertTrue(result.getStack().size() == 2);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("1")));
		assertTrue(result.getStack().toArray()[1].equals(new BigDecimal("2")));
		
		expression = "3 * 5 + * * 6 5";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.ERROR);
		assertTrue(result.getErrorCode().equals(ErrorCode.INSUFFICIENT_PARAMETERS.getCode()));
		assertTrue(result.getErrTerm().equals(OperatorCommandEnum.MUL.getCmd()));
		assertTrue(result.getErrTermOffset() == 11);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("11")));
		
		expression = "   ";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.ERROR);
		assertTrue(result.getErrorCode().equals(ErrorCode.EXPRESSION_BLANK.getCode()));
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("11")));
		
		expression = "20 -  ";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.SUCCESS);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("-9")));
		
		expression = "sqrt";
		result = calculator.exec(expression);
		assertTrue(result.getStatus() == Status.ERROR);
		assertTrue(result.getErrorCode().equals(ErrorCode.NEGATIVE.getCode()));
		assertTrue(result.getErrTerm().equals(OperatorCommandEnum.SQRT.getCmd()));
		assertTrue(result.getErrTermOffset() == 1);
		assertTrue(result.getStack().size() == 1);
		assertTrue(result.getStack().toArray()[0].equals(new BigDecimal("-9")));
	}
}
