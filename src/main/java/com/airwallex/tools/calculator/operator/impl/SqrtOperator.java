package com.airwallex.tools.calculator.operator.impl;

import java.math.BigDecimal;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.OperatorResult.Status;
import com.airwallex.tools.calculator.model.enums.OperatorCommandEnum;
import com.airwallex.tools.calculator.model.log.OperatorLogDeque;
import com.airwallex.tools.calculator.model.stack.CalculatorStack;

public class SqrtOperator extends AbstractOperator {

	public SqrtOperator(CalculatorStack calculatorStack, OperatorLogDeque operatorLogDeque) {
		super(OperatorCommandEnum.SQRT, calculatorStack, operatorLogDeque);
	}

	@Override
	public OperatorResult exec(String term) {
		OperatorResult resp = this.checkStackSize(1);
		if (resp.getStatus() != Status.SUCCESS) {
			// Insufficient parametes
			return resp;
		}

		BigDecimal parameter = this.calculatorStack.pop();
		// Add operator to cache
		this.offerOperatorLog(parameter);
		// Calculate and return
		BigDecimal result = parameter.multiply(parameter);
		this.calculatorStack.push(result);
		return resp;
	}
}
