package com.airwallex.tools.calculator;

import org.apache.commons.lang3.StringUtils;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.OperatorResult.Status;
import com.airwallex.tools.calculator.model.enums.ErrorCode;
import com.airwallex.tools.calculator.model.log.OperatorLogDeque;
import com.airwallex.tools.calculator.model.stack.CalculatorStack;
import com.airwallex.tools.calculator.model.stack.impl.RPNCalculatorStack;
import com.airwallex.tools.calculator.operator.OperatorAdapter;

public class RPNCalculator {

	// Operator Log Mgr
	private OperatorLogDeque operatorLogDeque;
	// Calculator Stack
	protected CalculatorStack calculatorStack;
	// Calculator Stack
	protected OperatorAdapter operatorAdapter;

	public RPNCalculator(int maxUndo) {
		operatorLogDeque = new OperatorLogDeque(maxUndo);
		calculatorStack = new RPNCalculatorStack();
		operatorAdapter = new OperatorAdapter(calculatorStack, operatorLogDeque);
	}

	public OperatorResult exec(String expression) {
		// Check expression whether is empty
		if (StringUtils.isBlank(expression)) {
			OperatorResult result = new OperatorResult(ErrorCode.EXPRESSION_BLANK);
			result.setStack(calculatorStack);
			return result;
		}

		// Split expression to terms
		String[] terms = expression.split("\\s");
		int offset = 0;
		for (String term : terms) {
			OperatorResult resp = operatorAdapter.exec(term);
			if (resp.getStatus() != Status.SUCCESS) {
				resp.setErrTermOffset(offset);
				resp.setErrTerm(term);
				break;
			}
			offset += term.length() + 1;
		}

		OperatorResult result = new OperatorResult();
		result.setStack(calculatorStack);
		return result;
	}

}
