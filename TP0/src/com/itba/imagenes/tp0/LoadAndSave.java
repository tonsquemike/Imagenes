package com.itba.imagenes.tp0;

import java.awt.image.BufferedImage;

import com.itba.imagenes.ParamsReader;

public class LoadAndSave {

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
		img = params.loadNextImage();
		params.saveImage(img);
	}
}
