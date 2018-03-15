package org.hbhk.test;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class NumberFormatTest {

	
	public static void main(String[] args) {
		BigDecimal b1 = new BigDecimal("11950.0");
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMaximumFractionDigits(0);
		String str  = format.format(b1);
		str = str.replaceAll(",", "");
		b1 = new BigDecimal(str);
		System.out.println(b1);
	}
}
