
package org.crusty.math;

import java.util.Random;

public class MathUtil {

	public static int bounds(int n, int min, int max) {
		if (n < min)
			n = min;
		if (n > max)
			n = max;
		return n;
	}
	
	public static int[] randomOrdering(int size) {
		Random r = new Random();
		/* Shuffle list of numbers */
		int[] nums = new int[size];
		// Fill with numbers
		for (int i = 0; i < size; i++) {
			nums[i] = i;
		}
		// Shuffle
		for (int i = 0; i < 4; i++) {
			int firstNum = r.nextInt(size);
			int secondNum = r.nextInt(size);
			// Swap
			int temp = nums[firstNum];
			nums[firstNum] = nums[secondNum];
			nums[secondNum] = temp;
		}
//		for (int i = 0; i < size; i++) {
//			System.out.println(">" + nums[i]);
//		}
		return nums;
	}
	
}
