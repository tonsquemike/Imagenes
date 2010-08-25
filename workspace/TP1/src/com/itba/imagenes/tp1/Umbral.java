package com.itba.imagenes.tp1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.itba.imagenes.ParamsReader;

public class Umbral {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ParamsReader params = null;
		try {
			ParamsReader.workPath += "tp1/";
			params = new ParamsReader(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		BufferedImage imgInput1 = null;

		try {
			imgInput1 = ImageIO.read(new File(params.inputImageName1 + "."
					+ params.imageFormatInput1));
		} catch (IOException e) {
			System.out.println(e);
		}

		double[] rgb1 = new double[3];
		double greyValue;

		for (int i = 0; i < imgInput1.getWidth(); i++) {
			for (int j = 0; j < imgInput1.getHeight(); j++) {

				imgInput1.getRaster().getPixel(i, j, rgb1);
				// Lo paso a gris con el promedio:
				greyValue = (rgb1[0] + rgb1[1] + rgb1[2]) / 3;

				// Le aplico umbral:
				if (greyValue <= params.scalar)
					greyValue = 0;
				else
					greyValue = 255;

				rgb1[0] = greyValue;
				rgb1[1] = greyValue;
				rgb1[2] = greyValue;

				imgInput1.getRaster().setPixel(i, j, rgb1);
			}
		}

		ImageIO.write(imgInput1, params.imageFormatOutput, new File(
				params.outputImageName + "." + params.imageFormatOutput));
	}
}