package com.airwallex.tools.calculator.operator.impl;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.enums.OperatorCommandEnum;
import com.airwallex.tools.calculator.model.log.OperatorLog;
import com.airwallex.tools.calculator.model.log.OperatorLogDeque;
import com.airwallex.tools.calculator.model.stack.CalculatorStack;

public class UndoOperator extends AbstractOperator {

	public UndoOperator(CalculatorStack calculatorStack, OperatorLogDeque operatorLogDeque) {
		super(OperatorCommandEnum.UNDO, calculatorStack, operatorLogDeque);
	}

	@Override
	public OperatorResult exec(String term) {
		OperatorResult resp = new OperatorResult();
		// Get operator log to handle undo command
		OperatorLog operatorLog = this.pollOperatorLog();
		if (operatorLog == null || !operatorLog.getCommand().getUndoFlag()) {
			// Not found any operator log or command not support undo, return directly
			return resp;
		}

		// Undo clear command by operator log
		if (operatorLog.getCommand() == OperatorCommandEnum.CLEAR) {
			// clear current statck
			this.calculatorStack.clear();
			this.calculatorStack.addAll(operatorLog.getParameter());
			return resp;
		}

		// Undo other command by operator log
		this.calculatorStack.pop();
		this.calculatorStack.addAll(operatorLog.getParameter());
		return resp;
	}
}
