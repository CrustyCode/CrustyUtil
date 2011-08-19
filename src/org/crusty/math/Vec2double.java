package org.crusty.math;

public class Vec2double {
	
	public double x, y;
	
	public Vec2double(double x, double y) {
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
	
	/** rotate Vector around point */
	public Vec2double rotate(Vec2double point, double angle) {
		double xx = ((x - point.x) * Math.cos(angle)) - ((point.y - y) * Math.sin(angle));
		double yy = ((point.y - y) * Math.cos(angle)) - ((x - point.x) * Math.sin(angle));
		Vec2double v = new Vec2double(xx, yy);
		return v;
	}

	/** Returns length */
	public double length() {
		return (double) Math.sqrt(x*x + y*y);
	}

	/** Returns new VECTOR - Scales vector to length 1 */
	public Vec2double normalise() {
		double len = (double) (1 / Math.sqrt(x*x + y*y));
//		x *= len;
//		y *= len;
		return new Vec2double(x*len, y*len);
	}

	/** Returns a new Vec2 */
	public Vec2double sub(Vec2double vec2double) {
		return new Vec2double(this.x - vec2double.x, this.y - vec2double.y);
	}
	
	/** Returns a new Vec2 */
	public Vec2double sub(Vec2int v) {
		return new Vec2double(this.x - v.x, this.y - v.y);
	}

	/** Returns a new Vec2 */
	public Vec2double add(Vec2double v) {
		return new Vec2double(this.x + v.x, this.y + v.y);
	}

	/** Returns a new Vec2 */
	public Vec2double clone() {
		return new Vec2double(this.x, this.y);
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
	
	/** Perpendicular */
	public Vec2double perp() {
		return new Vec2double(-this.y, this.x);
	}
	
	/** Formats Vec2 into (x, y) */
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}
