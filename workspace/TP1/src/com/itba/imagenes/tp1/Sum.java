package com.itba.imagenes.tp1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sum {

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

		BufferedImage imgInput1 = null;
		BufferedImage imgInput2 = null;
		BufferedImage imgOutput = new BufferedImage(params.width,
				params.height, BufferedImage.TYPE_INT_RGB);

		try {
			imgInput1 = ImageIO.read(new File(params.inputImageName1));
			imgInput2 = ImageIO.read(new File(params.inputImageName2));
		} catch (IOException e) {
			System.out.println(e);
		}

		ImageIO.write(img, params.outputImageFormat, new File(
				params.outputImageName));
	}
}