
package com.airwallex.tools.calculator.model.stack;

import java.math.BigDecimal;

/**
 * Calculator Stack
 * 
 * @author Justin
 *
 * @param <E>
 */
public interface CalculatorStack {
	
	public static int CAL_SCALE = 15;
	public static int DISPLAY_SCALE = 10;
	
	/**
	 * Push an item onto the top of a stack
	 * 
	 * @param item
	 * @return
	 */
	BigDecimal push(BigDecimal item);

	/**
	 * Add all
	 * 
	 * @param item
	 * @return
	 */
	void addAll(BigDecimal[] items);

	/**
	 * Remove the item at the top of the stack and returns this item as the
	 * value of this function.
	 * 
	 * @return
	 */
	BigDecimal pop();

	/**
	 * Remove all items by array.
	 * 
	 * @return
	 */
	BigDecimal[] toArray();

	/**
	 * Return the number of items in the stack
	 * 
	 * @return
	 */
	int size();

	/**
	 * Check the stack whether is empty.
	 * 
	 * @return
	 */
	boolean empty();

	/**
	 * Remove all of the items from a stack.
	 */
	void clear();
}
