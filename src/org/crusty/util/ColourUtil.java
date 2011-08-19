package org.crusty.util;

import java.awt.Color;

import org.crusty.math.MathUtil;

public class ColourUtil {

	public static Color opposite(Color c) {
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		return new Color(255 - r, 255 - g, 255 - b);
	}
	
	public static Color getGradientColor(Color c1, Color c2, int percentShift) {
		if (percentShift > 100)
			percentShift = 100;
		if (percentShift < 0)
			percentShift = 0;
		float shift = percentShift/100f;
		int difR = (c2.getRed() - c1.getRed());
		int difG = (c2.getGreen() - c1.getGreen());
		int difB = (c2.getBlue() - c1.getBlue());
		Color grad = new Color((MathUtil.bounds((int) (c1.getRed() + difR*shift), 0, 255)),
				(MathUtil.bounds((int) (c1.getGreen() + difG*shift), 0, 255)),
				(MathUtil.bounds((int) (c1.getBlue() + difB*shift), 0, 255)));
		return grad;
	}
	
	/**
	 * Adds a colour to a background colour and returns the new colour
	 */
	public static Color addToColour(Color bc, Color ac) {
		return new Color(	(int) ((ac.getAlpha()/255f) * ac.getRed() + (1 - (ac.getAlpha()/255f)) * bc.getRed()),
							(int) ((ac.getAlpha()/255f) * ac.getGreen() + (1 - (ac.getAlpha()/255f)) * bc.getGreen()),
							(int) ((ac.getAlpha()/255f) * ac.getBlue() + (1 - (ac.getAlpha()/255f)) * bc.getBlue()),
							(int) (Math.min(((bc.getAlpha() + ac.getAlpha())), 255)));
	}
	
}
