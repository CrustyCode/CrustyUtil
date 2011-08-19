package org.crusty.math;

import org.crusty.util.DoubleUtil;

/** Quick Vec3 implementation */
public class Vec3 {

	public double x, y, z;
	
	public Vec3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3 clone() {
		return new Vec3(x, y, z);
	}
	
	public double length() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vec3 normalise() {
		double len = this.length();
		Vec3 temp = new Vec3(x/len, y/len, z/len);
		return temp;
	}
	
	/** Rounds to two decimal places */
	public String toString() {
		return "[" + DoubleUtil.roundTwoDec(x) + ", " + DoubleUtil.roundTwoDec(y) + ", " + DoubleUtil.roundTwoDec(z) + "]";
	}
	
}
