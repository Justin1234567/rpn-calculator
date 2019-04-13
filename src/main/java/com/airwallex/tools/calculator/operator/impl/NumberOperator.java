package com.airwallex.tools.calculator.operator.impl;

import java.math.BigDecimal;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.enums.OperatorCommandEnum;
import com.airwallex.tools.calculator.model.log.OperatorLogDeque;
import com.airwallex.tools.calculator.model.stack.CalculatorStack;

public class NumberOperator extends AbstractOperator {

	public NumberOperator(CalculatorStack calculatorStack, OperatorLogDeque operatorLogDeque) {
		super(OperatorCommandEnum.NUMBER, calculatorStack, operatorLogDeque);
	}

	@Override
	public OperatorResult exec(String term) {
		BigDecimal parameter = new BigDecimal(term);

		// Add operator to cache
		this.offerOperatorLog(parameter);
		// Push to stack
		this.calculatorStack.push(parameter);
		return new OperatorResult();
	}
}
