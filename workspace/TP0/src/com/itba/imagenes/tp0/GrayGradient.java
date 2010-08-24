package com.itba.imagenes.tp0;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GrayGradient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParamsReader params = null;
		try {
			params = new ParamsReader(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		BufferedImage img = new BufferedImage(params.width, params.height,
				BufferedImage.TYPE_INT_RGB);

		// rgb = 0xFF 00=R FF=G 00=B; // green
		// Empiezo con blanco
		int rgb = 0xFFFFFF;
		for (int i = 0; i < params.width; i++) {
			for (int j = 0; j < params.height; j++) {
				img.setRGB(i, j, rgb);
			}

			rgb -= 0x020202;
		}

		try {
			ImageIO.write(img, params.outputImageFormat, new File(
					params.outputImageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}