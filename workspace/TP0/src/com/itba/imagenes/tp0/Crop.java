package com.itba.imagenes.tp0;

import java.awt.image.BufferedImage;

import com.itba.imagenes.ImageHandler;
import com.itba.imagenes.ParamsReader;

public class Crop {

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

		BufferedImage img = null;
		img = ImageHandler.read(params.inputImageName1,
				params.imageFormatInput1, params);

		BufferedImage croppedImage = new BufferedImage(params.width,
				params.height, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < params.width; i++) {
			for (int j = 0; j < params.height; j++) {
				croppedImage.setRGB(i, j, img.getRGB(i, j));
			}
		}

		ImageHandler.write(croppedImage, params.imageFormatOutput,
				params.outputImageName);
	}
}
