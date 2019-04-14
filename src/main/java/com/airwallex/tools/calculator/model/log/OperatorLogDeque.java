package com.airwallex.tools.calculator.model.log;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

import com.airwallex.tools.calculator.model.enums.OperatorCommandEnum;

/**
 * Operator log
 * 
 */
public class OperatorLogDeque {
	// Deque that used to save log
	private Deque<OperatorLog> deque;
	// Max undo size of deque
	private int maxUndoSize;

	public OperatorLogDeque(int maxUndoSize) {
		if (maxUndoSize <= 0) {
			throw new IllegalArgumentException("Illegal maxSize: " + maxUndoSize);
		}
		this.deque = new ArrayDeque<OperatorLog>();
		this.maxUndoSize = maxUndoSize;
	}

	public void offer(OperatorCommandEnum command, BigDecimal... parameter) {
		OperatorLog log = new OperatorLog(command, parameter);
		// Inserts the specified element at the front of this deque
		deque.offerFirst(log);
		if (deque.size() > maxUndoSize) {
			// Delete the longest log, if maxSize > deque.size()
			deque.pollLast();
		}
	}

	public OperatorLog pollFirst() {
		return deque.pollFirst();
	}
}
