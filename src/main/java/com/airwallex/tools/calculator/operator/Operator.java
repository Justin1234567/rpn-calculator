package com.airwallex.tools.calculator.operator;

import com.airwallex.tools.calculator.model.OperatorResult;
import com.airwallex.tools.calculator.model.enums.OperatorCommandEnum;

public interface Operator {

	/**
	 * Get operator of command
	 * 
	 * @return operator command
	 */
	OperatorCommandEnum getCommand();

	/**
	 * exec
	 * 
	 * @param parameter
	 * 
	 * @return result
	 */
	OperatorResult exec(String term);

}
