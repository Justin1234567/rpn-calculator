package com.airwallex.tools.calculator.model.stack.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Stack;

import com.airwallex.tools.calculator.model.stack.CalculatorStack;

/**
 * RPNCalculatorStack
 * 
 */
public class RPNCalculatorStack implements CalculatorStack {
	private Stack<BigDecimal> stack;

	public RPNCalculatorStack() {
		stack = new Stack<BigDecimal>();
	}

	@Override
	public BigDecimal push(BigDecimal item) {
		BigDecimal data = stack.push(item);
		return data;
	}

	@Override
	public void addAll(BigDecimal[] items) {
		if (items != null && items.length > 0) {
			Arrays.asList(items).stream().forEach(stack::push);
		}
	}

	@Override
	public BigDecimal pop() {
		BigDecimal data = stack.pop();
		return data;
	}

	public BigDecimal[] toArray() {
		if (stack.size() > 0) {
			Object[] array = stack.toArray();
			BigDecimal[] result = new BigDecimal[stack.size()];
			for (int i = 0; i < result.length; i++) {
				result[i] = (BigDecimal) array[i];
			}
			return result;
		}
		return null;
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public boolean empty() {
		return stack.empty();
	}

	@Override
	public void clear() {
		stack.clear();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("stack:");
		BigDecimal[] array = this.toArray();
		if (array != null) {
			for (BigDecimal bigDecimal : array) {
				bigDecimal.setScale(10, BigDecimal.ROUND_HALF_UP);
				sb.append(bigDecimal.toString());
				sb.append(" ");
			}
		}
		return sb.toString();
	}
}
