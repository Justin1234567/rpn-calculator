package com.airwallex.tools.calculator.operator.impl;

import java.math.BigDecimal;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.OperatorResult.Status;
import com.airwallex.tools.calculator.model.enums.OperatorCommandEnum;
import com.airwallex.tools.calculator.model.log.OperatorLogDeque;
import com.airwallex.tools.calculator.model.stack.CalculatorStack;

public class AddOperator extends AbstractOperator {

	public AddOperator(CalculatorStack calculatorStack, OperatorLogDeque operatorLogDeque) {
		super(OperatorCommandEnum.ADD, calculatorStack, operatorLogDeque);
	}

	@Override
	public OperatorResult exec(String term) {
		OperatorResult resp = this.checkStackSize(2);
		if (resp.getStatus() != Status.SUCCESS) {
			// Insufficient parametes
			return resp;
		}

		BigDecimal[] parameters = new BigDecimal[2];
		parameters[1] = this.calculatorStack.pop();
		parameters[0] = this.calculatorStack.pop();
		// Add operator to cache
		this.offerOperatorLog(parameters);
		// Calculate and return
		BigDecimal result = parameters[0].add(parameters[1]);
		this.calculatorStack.push(result);
		return resp;
	}
}
