package com.airwallex.tools.calculator.operator.impl;

import java.math.BigDecimal;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.OperatorResult.Status;
import com.airwallex.tools.calculator.model.enums.ErrorCode;
import com.airwallex.tools.calculator.model.enums.OperatorCommandEnum;
import com.airwallex.tools.calculator.model.log.OperatorLogDeque;
import com.airwallex.tools.calculator.model.stack.CalculatorStack;
import com.airwallex.tools.calculator.util.BigDecimalSqrt;

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
		// Check whether is negative
		if(parameter.compareTo(BigDecimal.ZERO) < 0) {
			// return number to stack
			this.calculatorStack.push(parameter);
			return new OperatorResult(ErrorCode.NEGATIVE);
		}
		
		// Add operator to cache
		this.offerOperatorLog(parameter);
		// Calculate and return
		BigDecimal result = BigDecimalSqrt.sqrt(parameter, 15, BigDecimal.ROUND_HALF_UP);
		this.calculatorStack.push(result);
		return resp;
	}
}
