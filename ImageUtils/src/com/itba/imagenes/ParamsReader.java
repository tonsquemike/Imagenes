package com.itba.imagenes;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedImageAdapter;

@SuppressWarnings("restriction")
public class ParamsReader {

	// Default param values if not passed by command line:
	public static String workPath = "/Users/iandrono/Documents/ITBA/Imagenes/tps/";
	public String inputImageName1 = "/Users/iandrono/Documents/ITBA/Imagenes/tps/tp0/input";
	public String inputImageName2 = "/Users/iandrono/Documents/ITBA/Imagenes/tps/tp0/input";
	public String outputImageName = "/Users/iandrono/Documents/ITBA/Imagenes/tps/tp0/output";
	public String imageFormatOutput = "jpg";
	public String imageFormatInput1 = "jpg";
	public String imageFormatInput2 = "jpg";
	public String oper = "suma";
	public String color = "R";

	public int width1 = 100;
	public int height1 = 100;
	public int width2 = 100;
	public int height2 = 100;
	public int defaultRGB = 0x00FF00;
	public double scalar = 0.5;

	public ParamsReader(String[] args) throws NumberFormatException,
			IllegalArgumentException, IllegalAccessException {
		String split[] = null;
		for (String arg : args) {
			split = arg.split("=");
			if (split.length >= 2) {
				try {
					Field aField = ParamsReader.class.getField(split[0]);
					if (aField.getType() == int.class) {
						aField.setInt(this, new Integer(split[1]));
					} else if (aField.getType() == String.class) {
						if (aField.getName().startsWith("input")
								|| aField.getName().startsWith("output"))
							aField.set(this, workPath + split[1]);
						else
							aField.set(this, split[1]);
					} else if (aField.getType() == double.class) {
						aField.setDouble(this, new Double(split[1]));
					}
				} catch (NoSuchFieldException e) {
					System.out.println("No such argument : " + split[0]
							+ ", Using default");
				}
			}
		}
		return;
	}

	public int imageIndex = 0;

	public BufferedImage loadNextImage() {
		String name = null;
		String format = null;
		int width = 0;
		int height = 0;

		switch (imageIndex) {
		case 0:
			name = inputImageName1;
			format = imageFormatInput1;
			width = width1;
			height = height1;
			break;
		case 1:
			name = inputImageName2;
			format = imageFormatInput2;
			width = width2;
			height = height2;
			break;
		}

		imageIndex++;

		BufferedImage image = null;
		File imageFile = new File(name + "." + format);

		if (format.equalsIgnoreCase("raw")) {
			FileInputStream imageFileStream = null;

			try {
				imageFileStream = new FileInputStream(imageFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			BufferedImage img = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					try {
						img.setRGB(j, i, imageFileStream.read());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			image = img;
		} else if (format.equalsIgnoreCase("pgm")
				|| format.equalsIgnoreCase("tif")) {
			RenderedImage rendimg = JAI.create("fileload", name + "." + format);
			BufferedImage bufferedImage = new RenderedImageAdapter(rendimg)
					.getAsBufferedImage();

			image = bufferedImage;
		} else {
			try {
				image = ImageIO.read(imageFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return image;
	}

	public void saveImage(BufferedImage img) {

		File outputImageFile = new File(outputImageName + "."
				+ imageFormatOutput);

		try {
			ImageIO.write(img, imageFormatOutput, outputImageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}