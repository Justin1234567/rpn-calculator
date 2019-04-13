package com.airwallex.tools.calculator.operator.impl;

import java.math.BigDecimal;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.OperatorResult.Status;
import com.airwallex.tools.calculator.model.enums.OperatorCommandEnum;
import com.airwallex.tools.calculator.model.log.OperatorLogDeque;
import com.airwallex.tools.calculator.model.stack.CalculatorStack;

public class SubOperator extends AbstractOperator {

	public SubOperator(CalculatorStack calculatorStack, OperatorLogDeque operatorLogDeque) {
		super(OperatorCommandEnum.SUB, calculatorStack, operatorLogDeque);
	}

	@Override
	public OperatorResult exec(String term) {
		OperatorResult resp = this.checkStackSize(2);
		if (resp.getStatus() != Status.SUCCESS) {
			// Insufficient parametes
			return resp;
		}

		BigDecimal[] parameters = new BigDecimal[2];
		parameters[0] = this.calculatorStack.pop();
		parameters[1] = this.calculatorStack.pop();
		// Add operator to cache
		this.offerOperatorLog(parameters);
		// Calculate and return
		BigDecimal result = parameters[1].subtract(parameters[0]);
		this.calculatorStack.push(result);
		return resp;
	}
}
