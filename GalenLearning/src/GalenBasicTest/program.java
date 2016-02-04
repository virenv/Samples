package GalenBasicTest;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = device.getDisplayMode().getWidth();
		int height = device.getDisplayMode().getHeight();
		System.out.println("Screen width and height are " + width + " and " + height);
	}

}
