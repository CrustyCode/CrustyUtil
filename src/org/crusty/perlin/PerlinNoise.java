package org.crusty.perlin;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.util.Random;

public class PerlinNoise {
	
	static Random r = new Random();
	
    private static ColorModel generateColorModel() {
        // Generate 16-color model
        byte[] r = new byte[16];
        byte[] g = new byte[16];
        byte[] b = new byte[16];

        r[0] = 0; g[0] = 0; b[0] = 0;
        r[1] = 0; g[1] = 0; b[1] = (byte)192;
        r[2] = 0; g[2] = 0; b[2] = (byte)255;
        r[3] = 0; g[3] = (byte)192; b[3] = 0;
        r[4] = 0; g[4] = (byte)255; b[4] = 0;
        r[5] = 0; g[5] = (byte)192; b[5] = (byte)192;
        r[6] = 0; g[6] = (byte)255; b[6] = (byte)255;
        r[7] = (byte)192; g[7] = 0; b[7] = 0;
        r[8] = (byte)255; g[8] = 0; b[8] = 0;
        r[9] = (byte)192; g[9] = 0; b[9] = (byte)192;
        r[10] = (byte)255; g[10] = 0; b[10] = (byte)255;
        r[11] = (byte)192; g[11] = (byte)192; b[11] = 0;
        r[12] = (byte)255; g[12] = (byte)255; b[12] = 0;
        r[13] = (byte)80; g[13] = (byte)80; b[13] = (byte)80;
        r[14] = (byte)192; g[14] = (byte)192; b[14] = (byte)192;
        r[15] = (byte)255; g[15] = (byte)255; b[15] = (byte)255;

        return new IndexColorModel(4, 16, r, g, b);
    }
	
    /**
     * Double values from 0 to 1
     * @param pixels
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage doubleArrayToBufferedImage(double[] pixels, int width, int height) {
    	BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    	Graphics2D g = (Graphics2D) bi.getGraphics();
    	for (int y = 0; y < height; y++) {
    		for (int x = 0; x < width; x++) {
    			int grey = (int) (pixels[width*y + x]*255);
    			if (grey > 0) {
					grey = Math.max(Math.min(grey, 255), 0);
	    			//g.setColor(new Color(grey, grey, grey));
					g.setColor(new Color(255, 255, 0, grey));
	    			g.drawLine(x, y, x, y);
    			} else {
    				grey = Math.min(Math.abs(grey), 255);
    				g.setColor(new Color(0, 0, 255, grey));
        			g.drawLine(x, y, x, y);
    			}
    			
    		}
    	}
    	return bi;
    }
    
    /**
     * Double values from 0 to 1
     * @param pixels
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage doubleArrayToHeightImage(double[] pixels, int width, int height) {
    	System.out.println("Starting Render");
    	BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    	Graphics2D g = (Graphics2D) bi.getGraphics();
    	for (int y = 0; y < height; y++) {
    		for (int x = 0; x < width; x++) {
    			int grey = (int) (pixels[width*y + x]*255);
    			if (grey > 0) {
					grey = Math.max(Math.min(grey, 255), 0);
	    			//g.setColor(new Color(grey, grey, grey));
					g.setColor(new Color(0, 255, 0, grey));
	    			g.drawLine(x, y, x, y - (int) (grey*0.30));
    			} else {
    				grey = Math.min(Math.abs(grey) + 150, 255);
    				g.setColor(new Color(100, 100, 255, grey));
        			g.drawLine(x, y, x, y);
    			}
    		}
    	}
    	System.out.println("Render Complete.");
    	return bi;
    }
    
    /**
     * @param pixels
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage doubleArrayToWormsImage(double[] pixels, int width, int height, Color colour) {
//    	System.out.println("Starting Render");
    	BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g = (Graphics2D) bi.getGraphics();
    	for (int y = 0; y < height; y++) {
    		for (int x = 0; x < width; x++) {
    			int grey = (int) (pixels[width*y + x]*255);
    			if (grey > 0) {
					//grey = Math.max(Math.min(grey, 255), 0);
	    			//g.setColor(new Color(grey, grey, grey));
					g.setColor(colour);
	    			g.drawLine(x, y, x, y);
    			}
    		}
    	}
//    	System.out.println("Render Complete.");
    	return bi;
    }
    
    public static BufferedImage pixelArrayToBufferedImage(byte[] pixels, int width, int height) {
        DataBuffer dbuf = new DataBufferByte(pixels, width * height, 0);
        //int numBanks = dbuf.getNumBanks(); // 1

        // Prepare a sample model that specifies a storage 4-bits 
        // of pixel datavd in an 8-bit data element
        int bitMasks[] = new int[] { (byte) 0xf };
        SampleModel sampleModel = new SinglePixelPackedSampleModel(
            DataBuffer.TYPE_BYTE, width, height, bitMasks);

        WritableRaster raster = Raster.createWritableRaster(sampleModel, dbuf, null);
        ColorModel colorModel = generateColorModel();
        
        // Combine the color model and raster into a buffered image
        BufferedImage image = new BufferedImage(colorModel, raster, false, null);//new java.util.Hashtable());
        
        return image;
    }

    public static double interpolatedNoise1(double x, double y) {
	    int integer_X = (int) x;
	    double fractional_X = x - integer_X;
	
	    int integer_Y = (int) y;
	    double fractional_Y = y - integer_Y;
	
	    // smoothNoise(byte[] pixels, int width, int height)
	    double v1 = smoothNoise(integer_X,     integer_Y);
	    double v2 = smoothNoise(integer_X + 1, integer_Y);
	    double v3 = smoothNoise(integer_X,     integer_Y + 1);
	    double v4 = smoothNoise(integer_X + 1, integer_Y + 1);
	
	    double i1 = cosineInterpolate(v1 , v2 , fractional_X);
	    double i2 = cosineInterpolate(v3 , v4 , fractional_X);
	
	    return cosineInterpolate(i1 , i2 , fractional_Y);
    }
    
    /**
     * Perlin Noise 2D
     * x, y, frequency, persistance, octaves, amplitude
     * @param x
     * @param y
     * @param frequency - Number of noise values between each 2D point
     * @param persistance - Multiplier asjusting amplitude in each iteration
     * @param octaves - Iterations over noise functions
     * @param amplitude - Maximum value added to total noise value
     * @return
     */
    public static double perlinNoise2D(double x, double y, double frequency, double persistance, double octaves, double amplitude) {
	    double total = 0;
	    for (int i = 0; i < octaves; i++) {
	        total = total + interpolatedNoise1(x * frequency, y * frequency) * amplitude;
	        frequency *= 2;
	        amplitude *= persistance;
	    }
	    
	    double cloudCoverage = 0.00;   // USER ADJUSTABLE
	    double cloudDensity = 1.0;    // USER ADJUSTABLE

	    total = (total + cloudCoverage) * cloudDensity;
//	    System.out.println(" " + total);
//	    if (total < -1)
//	    	total = -1;
//	    if (total > 1)
//	    	total = 1;
	    return total;
    }
    
    /**
     * 2D noise generation
     * @param x
     * @param y
     * @return
     */
    
//    static Random r = new Random();
//    int r1 = r.Next(1000, 10000);
//    int r2 = r.Next(100000, 1000000);
//    int r3 = r.Next(1000000000, 2000000000);
//
//    double Noise(int x, int y)
//    {
//        int n = x + y * 57;
//        n = (n<<13) ^ n;
//
//        return ( 1.0 - ( (n * (n * n * r1 + r2) + r3) & 0x7fffffff) / 1073741824.0);
//    } 
    
//    public static int rand(int a, int b) {
//    	return (r.nextInt(b - a) + a);
//    }
    
    /**
     * Generate random 2D Noise
     */
    public static double int2DNoise(int x, int y) {
		int r1 = 15731; 
		int r2 = 789221; 
		int r3 = 1376312589; 
	    int n = x + y * 57;
	    n = (n << 13) ^ n;
	    return ( 1.0 - ( (n * (n * n * r1 + r2) + r3) & 0x7fffffff) / 1073741824.0);    
    }
    
    /**
     * Interpolate between two points
     * @param a
     * @param b
     * @param x
     * @return
     */
	private static double cosineInterpolate(double a, double b, double x) {
		double ft = x * Math.PI;
		double f = (1 - Math.cos(ft)) * 0.5;
		return  a*(1-f) + b*f;
	}
	
//	private static int mm(int val, int min, int max) {
//		return Math.max(Math.min(val, max), min);
//	}
	
	public static double smoothNoise(int x, int y) {
		double corners = ( int2DNoise(x-1, y-1)+int2DNoise(x+1, y-1)+int2DNoise(x-1, y+1)+int2DNoise(x+1, y+1) ) / 16;
		double sides   = ( int2DNoise(x-1, y)  +int2DNoise(x+1, y)  +int2DNoise(x, y-1)  +int2DNoise(x, y+1) ) /  8;
		double center  =  int2DNoise(x, y) / 4;
		return corners + sides + center;
	}
	
}
