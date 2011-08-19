package org.crusty.math;

public class Vec2 {
	
	public double x, y;
	
	public Vec2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public void set(double f, double g) {
		this.x = f;
		this.y = g;
	}

	/** Returns length */
	public double length() {
		return (double) Math.sqrt(x*x + y*y);
	}

	/** Scales vector to length 1 returns new Vec */
	public Vec2 normalise() {
		double len = (double) (1 / Math.sqrt(x*x + y*y));
		double xx = x;
		double yy = y;
		xx *= len;
		yy *= len;
		return new Vec2(xx, yy);
	}

	/** Returns a new Vec2 */
	public Vec2 sub(Vec2 v) {
		return new Vec2(this.x - v.x, this.y - v.y);
	}
	
	/** Returns a new Vec2 */
	public Vec2 sub(Vec2int v) {
		return new Vec2(this.x - v.x, this.y - v.y);
	}

	/** Returns a new Vec2 */
	public Vec2 add(Vec2 v) {
		return new Vec2(this.x + v.x, this.y + v.y);
	}

	/** Returns a new Vec2 */
	public Vec2 clone() {
		return new Vec2(this.x, this.y);
	}
	
//	/** Exact equality check */
//	public boolean equalsTo(Vec2 v) {
//		return (v.x == this.x && v.y == this.y);
//	}
	
	/** Equality check with variance range */
	public boolean equalsTo(Vec2 v, double range) {
		return (Math.abs(this.x - v.x) < range && Math.abs(this.y - v.y) < range);
	}
	
	/** Dot Product */
	public static double dotProd(Vec2 v1, Vec2 v2) {
		return (v1.x*v2.x + v1.y*v2.y);
	}
	
	/** Angle between two vectors */
	public static double angleBetweenTwoVectors(Vec2 v1, Vec2 v2) {
		return Math.acos(dotProd(v1, v2));
	}
	
	/** Perpendicular */
	public Vec2 perp() {
		return new Vec2(-this.y, this.x);
	}
	
	/** Formats Vec2 into (x, y) */
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}
