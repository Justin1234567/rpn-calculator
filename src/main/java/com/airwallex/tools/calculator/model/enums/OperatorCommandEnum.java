package com.airwallex.tools.calculator.model.enums;

import org.apache.commons.lang3.StringUtils;

import com.airwallex.tools.calculator.model.OperatorResult;

/**
 * Operator cmd of calculator
 * 
 * @author Justin
 *
 */
public enum OperatorCommandEnum {

	ADD("+", true, "add operation"), SUB("-", true, "sub operation"), MUL("*", true, "mul operation"), DIV("/", true,
			"div operation"), SQRT("sqrt", true, "sqrt operation"), UNDO("undo", false, "undo operation"), CLEAR(
					"clear", true, "clear operation"), NUMBER("number", true, "clear operation");

	private String cmd;
	// Whether the command can be undo
	private boolean undoFlag;
	private String desc;

	private OperatorCommandEnum(String cmd, boolean undoFlag, String desc) {
		this.cmd = cmd;
		this.undoFlag = undoFlag;
		this.desc = desc;
	}

	public String getCmd() {
		return cmd;
	}

	public String getDesc() {
		return desc;
	}

	public boolean getUndoFlag() {
		return undoFlag;
	}

	public static OperatorResult isSupport(String cmd) {
		if (StringUtils.isBlank(cmd)) {
			return new OperatorResult(ErrorCode.UNSUPPORTED_CMD);
		}

		for (OperatorCommandEnum commandEnum : OperatorCommandEnum.values()) {
			if (commandEnum.getCmd().equals(cmd.trim().toLowerCase())) {
				return new OperatorResult();
			}
		}
		return new OperatorResult(ErrorCode.UNSUPPORTED_CMD);
	}
}
