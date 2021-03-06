package com.itba.imagenes.tp0;

import java.awt.image.BufferedImage;

import com.itba.imagenes.ParamsReader;

public class GrayGradient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParamsReader params = null;
		try {
			ParamsReader.workPath += "tp0/";
			params = new ParamsReader(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		BufferedImage img = new BufferedImage(params.width1, params.height1,
				BufferedImage.TYPE_INT_RGB);

		// rgb = 0xFF 00=R FF=G 00=B; // green
		// Empiezo con blanco
		int rgb = 0xFFFFFF;
		for (int i = 0; i < params.width1; i++) {
			for (int j = 0; j < params.height1; j++) {
				img.setRGB(i, j, rgb);
			}

			rgb -= 0x020202;
		}

		params.saveImage(img);
	}

}
