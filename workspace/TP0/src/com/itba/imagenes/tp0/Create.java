package com.itba.imagenes.tp0;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.itba.imagenes.ParamsReader;

public class Create {

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

		for (int i = 0; i < params.width; i++) {
			for (int j = 0; j < params.height; j++) {
				img.setRGB(i, j, params.defaultRGB);
			}
		}

		try {
			ImageIO.write(img, params.imageFormat, new File(
					params.outputImageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
