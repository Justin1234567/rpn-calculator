package com.airwallex.tools.calculator.model.enums;

public enum ErrorCode {

	INSUFFICIENT_PARAMETERS("100", "insufficient parametes"),
	UNSUPPORTED_CMD("101", "unsupported cmd"),
	EXPRESSION_BLANK("102", "expression is blank");

	private String code;
	private String desc;

	private ErrorCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
