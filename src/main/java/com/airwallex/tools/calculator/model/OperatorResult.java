
package com.airwallex.tools.calculator.model;

import com.airwallex.tools.calculator.model.enums.ErrorCode;
import com.airwallex.tools.calculator.model.stack.CalculatorStack;

public class OperatorResult {

	// Default value is success
	private Status status = Status.SUCCESS;
	// Calculator Stack
	private CalculatorStack stack;
	// Offset of error term
	private int errTermOffset;
	// error term
	private String errTerm;
	// error code
	private String errorCode;
	// error message
	private String errorMessage;

	public OperatorResult() {
		status = Status.SUCCESS;
	}

	public OperatorResult(ErrorCode errorCode) {
		status = Status.ERROR;
		this.errorCode = errorCode.getCode();
		this.errorMessage = errorCode.getDesc();
	}

	public CalculatorStack getStack() {
		return stack;
	}

	public void setStack(CalculatorStack stack) {
		this.stack = stack;
	}

	public int getErrTermOffset() {
		return errTermOffset;
	}

	public void setErrTermOffset(int errTermOffset) {
		this.errTermOffset = errTermOffset;
	}

	public String getErrTerm() {
		return errTerm;
	}

	public void setErrTerm(String errTerm) {
		this.errTerm = errTerm;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		status = Status.ERROR;
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		status = Status.ERROR;
		this.errorMessage = errorMessage;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {
		SUCCESS("success"), ERROR("error"), WARNING("warning");

		private final String value;

		private Status(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
}
