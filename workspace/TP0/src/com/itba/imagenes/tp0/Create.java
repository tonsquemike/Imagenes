package com.itba.imagenes.tp0;

import java.awt.image.BufferedImage;

import com.itba.imagenes.ImageHandler;
import com.itba.imagenes.ParamsReader;

public class Create {

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

		BufferedImage img = new BufferedImage(params.width, params.height,
				BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < params.width; i++) {
			for (int j = 0; j < params.height; j++) {
				img.setRGB(i, j, params.defaultRGB);
			}
		}

		ImageHandler.write(img, params.imageFormatOutput,
				params.outputImageName);
	}
}
