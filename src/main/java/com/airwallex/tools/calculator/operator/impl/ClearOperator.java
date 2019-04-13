package com.airwallex.tools.calculator.operator.impl;

import java.math.BigDecimal;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.enums.OperatorCommandEnum;
import com.airwallex.tools.calculator.model.log.OperatorLogDeque;
import com.airwallex.tools.calculator.model.stack.CalculatorStack;

public class ClearOperator extends AbstractOperator {

	public ClearOperator(CalculatorStack calculatorStack, OperatorLogDeque operatorLogDeque) {
		super(OperatorCommandEnum.CLEAR, calculatorStack, operatorLogDeque);
	}

	@Override
	public OperatorResult exec(String term) {
		BigDecimal[] parameters = this.calculatorStack.toArray();
		if (parameters != null && parameters.length > 0) {
			// Add operator to cache
			this.offerOperatorLog(parameters);
			// Calculate and return
			this.calculatorStack.clear();
		}
		return new OperatorResult();
	}
}
