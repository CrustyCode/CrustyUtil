package org.crusty.math;

public class Matrix {
	public double[][] values;
	public int rows, cols;
	
	public Matrix(int rows, int cols) {
		this.rows = rows; 
		this.cols = cols;
		values = new double[rows][cols];
	}
	
	/**
	 * 2 x 3 matrix 	{ 	{ 1, 2, 3 }, 
	 * 						{ 4, 5, 6 } 	}
	 * @param values
	 */
	public void setValues(double[][] values) {
//		System.out.println(values.length + " : " + values[0].length);
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) { 
//				System.out.println("x: " + x + " y: " + y);
//				System.out.println("" + values[x][y]);
				this.values[x][y] = values[x][y];
			}
		}
	}
	
	public void setValue(int row, int col, double d) {
		this.values[row][col] = d;
	}
	
	public Matrix mult(Matrix m) {
		int rowsA = this.values.length; 		// p
		int columnsA = this.values[0].length;  	// q
		int rowsB = m.values.length;        	// q
		int columnsB = m.values[0].length;  	// r
		Matrix c = new Matrix(rowsA, columnsB);
		if (columnsA == rowsB) {
			for (int i = 0; i < rowsA; i++) {
				for (int j = 0; j < columnsB; j++) {
					c.setValue(i, j, 0);
					for (int k = 0; k < columnsA; k++) {
						c.setValue(i, j, c.values[i][j] + this.values[i][k] * m.values[k][j]);
					}
				}
			}
		} else {
			System.out.println("M1: " + rows + " x " + cols);
			System.out.println("M2: " + m.rows + " x " + m.cols);
			System.out.println("Matrix multiplication error. Wrong sizes.");
			StackTraceElement[] traceElements = Thread.currentThread().getStackTrace();
			for (int i = 0; i < traceElements.length; i++) {
				System.err.println(traceElements[i].toString());
			}
			System.exit(-1);
		}
		return c;
	}
	
	public void add(Matrix m) {
		if (rows != m.rows || cols != m.cols) {
			System.out.println("M1: " + rows + " x " + cols);
			System.out.println("M2: " + m.rows + " x " + m.cols);
			System.err.println("Matrix can't be added to. Wrong size.");
			return;
		}
		for (int y = 0; y < cols; y++) {
			for (int x = 0; x < rows; x++) {
				values[x][y] += m.values[x][y];
			}
		}
	}
	
	public void sub(Matrix m) {
		if (rows != m.rows || cols != m.cols) {
			System.err.println("Matrix can't be subtracted to. Wrong size.");
			return;
		}
		for (int y = 0; y < cols; y++) {
			for (int x = 0; x < rows; x++) {
				values[x][y] -= m.values[x][y];
			}
		}
	}
	
	public String toString() {
		String s = "[";
		for (int x = 0; x < rows; x++) {
			if (x > 0)
				s += " ";
			s += "[";
			for (int y = 0; y < cols; y++) {
				s += "" + values[x][y];
				if (y < cols - 1)
					s += ", ";
			}
			s += "]";
			if (x + 1 < rows) {
				s += "\n";
			}
		}
		s += "]";
		return s;
	}
}
