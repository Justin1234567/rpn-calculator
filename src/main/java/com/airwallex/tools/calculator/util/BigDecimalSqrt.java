package com.airwallex.tools.calculator.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigDecimalSqrt {
	
	private final static BigInteger HUNDRED = BigInteger.valueOf(100);

	public static BigDecimal sqrt(BigDecimal number, int scale, int roundingMode) {
		if (number.compareTo(BigDecimal.ZERO) < 0) {
			throw new ArithmeticException("sqrt with negative");
		}
		BigInteger integer = number.toBigInteger();
		StringBuffer sb = new StringBuffer();
		String strInt = integer.toString();
		int lenInt = strInt.length();
		if (lenInt % 2 != 0) {
			strInt = '0' + strInt;
			lenInt++;
		}
		BigInteger res = BigInteger.ZERO;
		BigInteger rem = BigInteger.ZERO;
		for (int i = 0; i < lenInt / 2; i++) {
			res = res.multiply(BigInteger.TEN);
			rem = rem.multiply(HUNDRED);

			BigInteger temp = new BigInteger(strInt.substring(i * 2, i * 2 + 2));
			rem = rem.add(temp);

			BigInteger j = BigInteger.TEN;
			while (j.compareTo(BigInteger.ZERO) > 0) {
				j = j.subtract(BigInteger.ONE);
				if (((res.add(j)).multiply(j)).compareTo(rem) <= 0) {
					break;
				}
			}

			res = res.add(j);
			rem = rem.subtract(res.multiply(j));
			res = res.add(j);
			sb.append(j);
		}
		
		if(rem.compareTo(BigInteger.ZERO) > 0) {
			sb.append('.');
			BigDecimal fraction = number.subtract(number.setScale(0, BigDecimal.ROUND_DOWN));
			int fracLen = (fraction.scale() + 1) / 2;
			fraction = fraction.movePointRight(fracLen * 2);
			String strFrac = fraction.toPlainString();
			for (int i = 0; i < scale; i++) {
				res = res.multiply(BigInteger.TEN);
				rem = rem.multiply(HUNDRED);
	
				if (i < fracLen) {
					BigInteger temp = new BigInteger(strFrac.substring(i * 2, i * 2 + 2));
					rem = rem.add(temp);
				}
	
				BigInteger j = BigInteger.TEN;
				while (j.compareTo(BigInteger.ZERO) > 0) {
					j = j.subtract(BigInteger.ONE);
					if (((res.add(j)).multiply(j)).compareTo(rem) <= 0) {
						break;
					}
				}
				res = res.add(j);
				rem = rem.subtract(res.multiply(j));
				res = res.add(j);
				sb.append(j);
			}
		}
		String result = new BigDecimal(sb.toString()).setScale(scale, roundingMode).stripTrailingZeros().toPlainString();
		return new BigDecimal(result.toString());
	}

	public static BigDecimal sqrt(BigDecimal number, int scale) {
		return sqrt(number, scale, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal sqrt(BigDecimal number) {
		int scale = number.scale() * 2;
		if (scale < 50)
			scale = 50;
		return sqrt(number, scale, BigDecimal.ROUND_HALF_UP);
	}
}