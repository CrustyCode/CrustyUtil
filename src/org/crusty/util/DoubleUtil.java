package org.crusty.util;

import java.text.DecimalFormat;

public class DoubleUtil {
	public static double roundTwoDec(double d) {
    	DecimalFormat twoDForm = new DecimalFormat("#.##");
    	return Double.valueOf(twoDForm.format(d));
	}
}
