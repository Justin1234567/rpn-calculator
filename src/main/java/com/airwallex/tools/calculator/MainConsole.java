package com.airwallex.tools.calculator;

import java.io.Console;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.OperatorResult.Status;
import com.airwallex.tools.calculator.model.enums.ErrorCode;

public class MainConsole {
	public static void main(String[] args) {

		Console console = System.console();
		if (console == null) {
			System.err.println("No console.");
			System.exit(1);
		}
		System.out.println("======================================================");
		System.out.println("| RPN Calculator Created by Justin                   |");
		System.out.println("| Supported operators: +, -, *, /, sqrt, clear, undo |");
		System.out.println("| Please place space between numbers and operators.  |");
		System.out.println("| Example input: 5 5 *                               |");
		System.out.println("| 'Ctrl+C' to quit                                   |");
		System.out.println("======================================================");

		// Init RPNCalculator
		RPNCalculator calculator = new RPNCalculator(100);
		OperatorResult result;

		while (true) {
			String expression = console.readLine(": ");
			result = calculator.exec(expression);
			
			if (result.getStatus() == Status.SUCCESS) {
				System.out.println(result.getStack().toString());
			} else if (result.getErrorCode().equals(ErrorCode.EXPRESSION_BLANK.getCode())) {
				System.out.println(result.getErrorMessage());
				System.out.println(result.getStack().toString());
			} else {
				int offset = result.getErrTermOffset();
				System.out.println("operator " + result.getErrTerm() + " (position: " + offset  + "):" + result.getErrorMessage());
				// Print result
				System.out.println(result.getStack().toString());
				String unExecExpression = expression.substring(offset, expression.length());
				System.out.println("(expression " + unExecExpression + " were not execed, due to the previous error)");
			}
		}
	}
}
