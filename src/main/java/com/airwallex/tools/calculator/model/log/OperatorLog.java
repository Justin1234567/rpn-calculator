package com.airwallex.tools.calculator.model.log;

import java.math.BigDecimal;

import com.airwallex.tools.calculator.model.enums.OperatorCommandEnum;

/**
 * Operator Log
 * 
 * @author Justin
 *
 */
public class OperatorLog {
	// Operator Command
	private OperatorCommandEnum command;
	// If command equals to CLEAR, parameter should be snapshot
	// RPNCalculatorStack
	private BigDecimal[] parameter;

	public OperatorLog(OperatorCommandEnum command, BigDecimal... parameter) {
		this.command = command;
		this.parameter = parameter;
	}

	public OperatorCommandEnum getCommand() {
		return command;
	}

	public BigDecimal[] getParameter() {
		return parameter;
	}
}
