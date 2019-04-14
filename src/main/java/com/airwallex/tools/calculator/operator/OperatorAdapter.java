package com.airwallex.tools.calculator.operator;

import java.util.HashMap;
import java.util.Map;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.OperatorResult.Status;
import com.airwallex.tools.calculator.model.enums.OperatorCommandEnum;
import com.airwallex.tools.calculator.model.log.OperatorLogDeque;
import com.airwallex.tools.calculator.model.stack.CalculatorStack;
import com.airwallex.tools.calculator.operator.impl.AddOperator;
import com.airwallex.tools.calculator.operator.impl.ClearOperator;
import com.airwallex.tools.calculator.operator.impl.DivOperator;
import com.airwallex.tools.calculator.operator.impl.MulOperator;
import com.airwallex.tools.calculator.operator.impl.NumberOperator;
import com.airwallex.tools.calculator.operator.impl.SqrtOperator;
import com.airwallex.tools.calculator.operator.impl.SubOperator;
import com.airwallex.tools.calculator.operator.impl.UndoOperator;
import com.airwallex.tools.calculator.util.NumberUtil;

public class OperatorAdapter {

	private Map<String, Operator> supportOperators;
	// Operator Log Mgr
	private OperatorLogDeque operatorLogDeque;
	// Calculator Stack
	private CalculatorStack calculatorStack;

	public OperatorAdapter(CalculatorStack calculatorStack, OperatorLogDeque operatorLogDeque) {
		this.calculatorStack = calculatorStack;
		this.operatorLogDeque = operatorLogDeque;
		// regist all operator
		this.registOperator();
	}

	private void registOperator() {
		supportOperators = new HashMap<String, Operator>(16);
		supportOperators.put(OperatorCommandEnum.ADD.getCmd(), new AddOperator(calculatorStack, operatorLogDeque));
		supportOperators.put(OperatorCommandEnum.CLEAR.getCmd(), new ClearOperator(calculatorStack, operatorLogDeque));
		supportOperators.put(OperatorCommandEnum.DIV.getCmd(), new DivOperator(calculatorStack, operatorLogDeque));
		supportOperators.put(OperatorCommandEnum.MUL.getCmd(), new MulOperator(calculatorStack, operatorLogDeque));
		supportOperators.put(OperatorCommandEnum.NUMBER.getCmd(), new NumberOperator(calculatorStack, operatorLogDeque));
		supportOperators.put(OperatorCommandEnum.SQRT.getCmd(), new SqrtOperator(calculatorStack, operatorLogDeque));
		supportOperators.put(OperatorCommandEnum.SUB.getCmd(), new SubOperator(calculatorStack, operatorLogDeque));
		supportOperators.put(OperatorCommandEnum.UNDO.getCmd(), new UndoOperator(calculatorStack, operatorLogDeque));
	}

	public OperatorResult exec(String term) {
		OperatorResult resp = new OperatorResult();
		// check whether is number
		term = term.trim().toLowerCase();
		if (NumberUtil.isNum(term)) {
			Operator operator = supportOperators.get(OperatorCommandEnum.NUMBER.getCmd());
			resp = operator.exec(term);
			return resp;
		}

		// check term whether is valid command
		OperatorResult checkResp = OperatorCommandEnum.isSupport(term);
		if (checkResp.getStatus() != Status.SUCCESS) {
			return checkResp;
		}

		// Exec calculate
		Operator operator = supportOperators.get(term.trim().toLowerCase());
		resp = operator.exec(term);
		return resp;
	}
}
