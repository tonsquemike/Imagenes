package com.itba.imagenes.tp1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

enum TYPE {
	TIF, RAW, BMP, JPG
}

enum COLOR {
	RED, GREEN, BLUE;
}

public class Histogram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedImage testImg = Histogram
				.getImage(
						"/home/pabloks/workspace/Imagenes/workspace/TP1/images/keys.jpg",
						TYPE.JPG);
		if (testImg != null) {
			BufferedImage newImg = Histogram.getHistogram(testImg, COLOR.BLUE);
			Histogram
					.writeImage(
							newImg,
							"/home/pabloks/workspace/Imagenes/workspace/TP1/images/hist-blue.jpg",
							TYPE.JPG);
			newImg = Histogram.getHistogram(testImg, COLOR.RED);
			Histogram
					.writeImage(
							newImg,
							"/home/pabloks/workspace/Imagenes/workspace/TP1/images/hist-red.jpg",
							TYPE.JPG);
			newImg = Histogram.getHistogram(testImg, COLOR.GREEN);
			Histogram
					.writeImage(
							newImg,
							"/home/pabloks/workspace/Imagenes/workspace/TP1/images/hist-green.jpg",
							TYPE.JPG);
			System.out.println("ok");
		}

		System.out.println("no");
	}

	public static BufferedImage negative(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage newImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		double[] rgb = new double[3];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				image.getRaster().getPixel(i, j, rgb);
				rgb[0] = 255 - rgb[0];
				rgb[1] = 255 - rgb[1];
				rgb[2] = 255 - rgb[2];
				newImage.getRaster().setPixel(i, j, rgb);
			}
		}

		return newImage;
	}

	public static BufferedImage blackAndWhite(BufferedImage image, COLOR color) {
		int width = image.getWidth();
		int height = image.getHeight();
		int index;
		double[] rgb = new double[3];

		BufferedImage newImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		if (color == COLOR.RED) {
			index = 0;
		} else if (color == COLOR.GREEN) {
			index = 1;
		} else {
			index = 2;
		}

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				image.getRaster().getPixel(i, j, rgb);

				rgb[0] = rgb[index];
				rgb[1] = rgb[index];
				rgb[2] = rgb[index];
				newImage.getRaster().setPixel(i, j, rgb);
			}
		}

		return newImage;
	}

	public static BufferedImage getImage(String Path, TYPE type) {
		BufferedImage img = null;

		try {
			if (type == TYPE.JPG)
				img = ImageIO.read(new File(Path));
			else if (type.equals(TYPE.RAW))
				// img = ImageIO.read ( new ByteArrayInputStream ( new
				// FileInputStream(new File(Path))) );
				;
			else if (type.equals(TYPE.BMP))
				;
			else
				img = ImageIO.read(new File(Path));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No pudo leerse le file");
		}

		return img;
	}

	public static BufferedImage getHistogram(BufferedImage img, COLOR color) {
		int width = img.getWidth();
		int height = img.getHeight();
		int index;
		double[] rgb = new double[3];
		int[] histogram = new int[256];

		if (color == COLOR.RED) {
			index = 0;
		} else if (color == COLOR.GREEN) {
			index = 1;
		} else {
			index = 2;
		}

		int max = 0;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				img.getRaster().getPixel(i, j, rgb);
				if (++histogram[(int) rgb[index]] > max)
					max = histogram[(int) rgb[index]];

			}
		}

		width = 256;
		height = 256;

		BufferedImage newImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		int[] white = { 255, 255, 255 };

		for (int i = 0; i < newImage.getWidth(); i++) {
			for (int j = 0; j < newImage.getHeight(); j++) {
				newImage.getRaster().setPixel(i, j, white);
			}
		}

		int[] black = { 0, 0, 0 };
		int y = 0;

		System.out.println(newImage.getHeight() + " " + max);

		for (int i = 0; i < newImage.getWidth(); i++) {
			y = histogram[i] * 255 / max;
			System.out.println(i + " " + y);
			for (int j = 0; j < y; j++) {
				newImage.getRaster().setPixel(i, height - 1 - j, black);
			}
		}

		return newImage;
	}

	public static boolean writeImage(BufferedImage img, String Path, TYPE type) {
		try {
			ImageIO.write(img, type.toString(), new File(Path));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
