package org.crusty.mandelbrot;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.util.Random;

public class Mandelbrot {

	public static double[][] goodShots = {
		{ 5.0159471376832484E-5, 5.0159471376832484E-5, -1.0178751793448342, -0.28309454002182344, 200 },
		{ 0.0013726010306413083, 0.0013726010306413083, 0.2862668957266695, 0.011798508332312656, 200 },
		{ 3.4315025766032707E-4, 3.4315025766032707E-4, 0.284881426561366, 0.011167969733861806, 200 },
		{ 6.420066132252917E-5, 6.420066132252917E-5, 0.3655279214209799, 0.5926144641149701, 200 },
		{ 5.015676665822591E-7, 5.015676665822591E-7, 0.36552962675104633, 0.5926171725803696, 200 },
		{ 1.5673989580695598E-8, 1.5673989580695598E-8, 0.3655295954030671, 0.5926171569063801, 200 },
		{ 0.009267201298286575, 0.009267201298286575, -1.3513821137473638, 0.06580581321556553, 200 },
		{ 4.525000633928992E-6, 4.525000633928992E-6, -1.3509775786906906, 0.06586509072387, 200 },
		{ 5.65625079241124E-7, 5.65625079241124E-7, -1.3509775221281826, 0.06586509072387, 200 },
		{ 2.0091873562027716E-8, 2.0091873562027716E-8, -1.4647974010165048, -1.6769268880848882E-5, 200 },
		{ 6.278710488133661E-10, 6.278710488133661E-10, -1.4647973998863368, -1.6770838558470915E-5, 200 },
		{ 0.021169953158512722, 0.021169953158512722, -0.3779590728196045, -0.6638161284395212, 200 }
		
	};
	
	public static Image render(int width, int height, double XZ, double YZ, double XA, double YA, int maxIteration) {

		int alpha;
		int red;
		int green;
		int blue;
//		int maxIteration = 200;
		
		int[] pixels = new int [width * height];
    	
    	int i = 0;
    	for (int y = 0; y < height; y++) {
    		for (int x = 0; x < width; x++) {

    		    double X0 = (i % width) * XZ/width - (XZ/2) + XA;
    		    double Y0 = ((i - X0) / width) * YZ/height - (YZ/2) + YA;

    		    double X = 0;
    		    double Y = 0;
    		    int Iteration = 0;
    		    
    		    while (X*X + Y*Y <= (2*2) & Iteration < maxIteration) {
    		    	double TempX = X*X - Y*Y + X0;
    		        Y = 2 * X * Y + Y0;
    		        X = TempX;
    		        Iteration++;
    		    }
    		    if (Iteration == maxIteration) {
    		    	red = 255;
        			green = 255;
        			blue = 255;
    		    } else {
    		    	red = (int) ((double) Iteration/maxIteration * 255);
        			green = (int) ((double) Iteration/maxIteration * 255);
        			blue = 0;
    		    }
    			alpha = 255;
    			
    			pixels[i] = (alpha << 24) | (red << 16) | (green << 8) | blue;
    			i++;
    		}
    	}
    	
    	return getImageFromArray(pixels, width, height);
	}
	
	public static Image renderRandom(int width, int height) {
//	    double XZ = 4; // Zoom Level (Low numbers are more zoomed in)
//	    double YZ = 4;
//	    double XA = -0.5; // Adjustment (View a different area of the set)
//	    double YA = 0;
		
		Random r = new Random();
		double XZ, YZ, XA, YA;
//		XZ = YZ = r.nextDouble()*2;
//		XA = r.nextDouble() - 0.5;
//		YA = r.nextDouble() - 0.5;
		int maxIteration;
		int rand = r.nextInt(goodShots.length);
		System.out.println("Background index: " + rand);
		XZ = goodShots[rand][0];
		YZ = goodShots[rand][1];
		XA = goodShots[rand][2];
		YA = goodShots[rand][3];
		maxIteration = (int) goodShots[rand][4];
		
		return render(width, height, XZ, YZ, XA, YA, maxIteration);
	}
	
	public static BufferedImage getImageFromArray(int[] pixels, int width, int height) {
		
		Image img =  Toolkit.getDefaultToolkit().
			createImage(new MemoryImageSource(width, height, pixels, 0, width));
		BufferedImage bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bimg.getGraphics().drawImage(img, 0, 0, null);
		
	    return bimg;
	}
	
}
