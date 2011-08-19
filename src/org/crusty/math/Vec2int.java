package org.crusty.math;

public class Vec2int {
	
	public int x;
	public int y;
	
	public Vec2int(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void set(int f, int g) {
		this.x = f;
		this.y = g;
	}
	
	/** Returns length */
	public float length() {
		return (float) Math.sqrt(x*x + y*y);
	}
	
	/** Returns a new Vec2int */
	public Vec2int add(Vec2int v) {
		return new Vec2int(this.x + v.x, this.y + v.y);
	}
	
	/** Returns a new Vec2 */
	public Vec2 add(Vec2 v) {
		return new Vec2(this.x + v.x, this.y + v.y);
	}
	
	/** Returns a new Vec2int */
	public Vec2int sub(Vec2int v) {
		return new Vec2int(this.x - v.x, this.y - v.y);
	}
	
	/** Returns a new Vec2 */
	public Vec2 sub(Vec2 v) {
		return new Vec2(this.x - v.x, this.y - v.y);
	}
	
//	/** Exact equality check */
//	public boolean equalsTo(Vec2int v) {
//		return (v.x == this.x && v.y == this.y);
//	}
//	
//	/** Equality check with variance range */
//	public boolean equalsTo(Vec2int v, float range) {
//		return (Math.abs(this.x - v.x) < range && Math.abs(this.y - v.y) < range);
//	}
	
	public boolean equals(Object v) {
		return (((Vec2int) v).x == this.x && ((Vec2int) v).y == this.y);
	}
	
	public Vec2int clone() {
		return new Vec2int(this.x, this.y);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	
	public int hashCode() {
		return (String.valueOf(this.x) + String.valueOf(this.y)).hashCode();
	}
	
}
