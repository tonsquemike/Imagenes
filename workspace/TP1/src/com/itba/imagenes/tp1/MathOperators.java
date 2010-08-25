package com.itba.imagenes.tp1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.itba.imagenes.ParamsReader;

public class MathOperators {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ParamsReader params = null;
		try {
			params = new ParamsReader(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		BufferedImage imgInput1 = null;
		BufferedImage imgInput2 = null;

		try {
			imgInput1 = ImageIO.read(new File(params.inputImageName1));
			imgInput2 = ImageIO.read(new File(params.inputImageName2));
		} catch (IOException e) {
			System.out.println(e);
		}

		int maxHeight = (imgInput1.getHeight() > imgInput2.getHeight() ? imgInput1
				.getHeight() : imgInput2.getHeight());

		int maxWidth = (imgInput1.getWidth() > imgInput2.getWidth() ? imgInput1
				.getWidth() : imgInput2.getWidth());

		BufferedImage imgOutput = new BufferedImage(maxWidth, maxHeight,
				BufferedImage.TYPE_INT_RGB);

		double[] rgb1 = new double[3];
		double[] rgb2 = new double[3];
		double[] out = new double[3];

		for (int i = 0; i < maxWidth; i++) {
			for (int j = 0; j < maxHeight; j++) {
				imgInput1.getRaster().getPixel(i, j, rgb1);
				imgInput2.getRaster().getPixel(i, j, rgb2);

				if (params.oper.equalsIgnoreCase("suma")) {
					out[0] = rgb1[0] + rgb2[0];
					out[0] = rgb1[0] + rgb2[0];
					out[0] = rgb1[0] + rgb2[0];
				} else if (params.oper.equalsIgnoreCase("resta")) {
					out[0] = rgb1[0] - rgb2[0];
					out[0] = rgb1[0] - rgb2[0];
					out[0] = rgb1[0] - rgb2[0];
				} else if (params.oper.equalsIgnoreCase("mult")) {
					out[0] = rgb1[0] * rgb2[0];
					out[0] = rgb1[0] * rgb2[0];
					out[0] = rgb1[0] * rgb2[0];
				} else
					throw new Exception("Parametro invalido");

				// Si se va de 0-255 lo mando a 0
				if (out[0] < 0 || out[0] > 255)
					out[0] = 0;
				if (out[1] < 0 || out[1] > 255)
					out[1] = 0;
				if (out[2] < 0 || out[2] > 255)
					out[2] = 0;

				imgOutput.getRaster().setPixel(i, j, out);
			}
		}

		ImageIO.write(imgOutput, params.imageFormat, new File(
				params.outputImageName));
	}
}
