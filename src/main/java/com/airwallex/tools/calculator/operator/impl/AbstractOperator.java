package com.airwallex.tools.calculator.operator.impl;

import java.math.BigDecimal;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.enums.ErrorCode;
import com.airwallex.tools.calculator.model.enums.OperatorCommandEnum;
import com.airwallex.tools.calculator.model.log.OperatorLog;
import com.airwallex.tools.calculator.model.log.OperatorLogDeque;
import com.airwallex.tools.calculator.model.stack.CalculatorStack;
import com.airwallex.tools.calculator.operator.Operator;

/**
 * Abstract Operator
 * 
 * @author Justin
 *
 */
public abstract class AbstractOperator implements Operator {
	// Operator Command
	private OperatorCommandEnum command;
	// Operator Log Mgr
	private OperatorLogDeque operatorLogDeque;
	// Calculator Stack
	protected CalculatorStack calculatorStack;

	public AbstractOperator(OperatorCommandEnum command, CalculatorStack calculatorStack,
			OperatorLogDeque operatorLogDeque) {
		this.command = command;
		this.calculatorStack = calculatorStack;
		this.operatorLogDeque = operatorLogDeque;
	}

	/**
	 * Get operator of command
	 * 
	 * @return operator command
	 */
	@Override
	public OperatorCommandEnum getCommand() {
		return command;
	}

	@Override
	public abstract OperatorResult exec(String term);

	protected OperatorResult checkStackSize(int count) {
		// Check parameters whether is insufficient
		if (this.calculatorStack.size() < count) {
			return new OperatorResult(ErrorCode.INSUFFICIENT_PARAMETERS);
		}
		return new OperatorResult();
	}

	/**
	 * offer operator log to cache
	 * 
	 * @param command
	 * @param parameter
	 */
	protected void offerOperatorLog(BigDecimal... parameter) {
		operatorLogDeque.offer(this.getCommand(), parameter);
	}

	/**
	 * poll operator log from cache
	 * 
	 */
	protected OperatorLog pollOperatorLog() {
		return operatorLogDeque.pollFirst();
	}
}
