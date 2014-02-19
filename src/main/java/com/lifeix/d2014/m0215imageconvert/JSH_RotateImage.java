package com.lifeix.d2014.m0215imageconvert;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JSH_RotateImage {

	private static final int ANGLE = 90;
	private static final String READ_PATH = "/usr/share/shotwell/icons/shotwell-street.jpg";
	private static final String WIRTE_PATH = "/tmp/shotwell-street.jpg";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File(READ_PATH);
		if(file.exists()) {
			try {
				BufferedImage imagesrc = ImageIO.read(file);
				BufferedImage iamge = rotate(imagesrc);
				File wirte = new File(WIRTE_PATH);
				if(wirte.exists()) {
					wirte.delete();
				}
				ImageIO.write(iamge, "jpg", wirte);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Generate image stream
	 * @param src
	 * @return
	 */
	private static BufferedImage rotate(Image src) {
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		Rectangle rectangle = dealRotateSize(new Rectangle(new Dimension(width, height)));
		BufferedImage image = new BufferedImage(rectangle.width, rectangle.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.translate((rectangle.width - width) / 2, (rectangle.height - height) / 2);
		graphics.rotate(Math.toRadians(ANGLE), width/2, height/2);
		graphics.drawImage(src, null, null);
		return image;
	}
	/**
	 * Calculate rotate size
	 * @param src
	 * @return
	 */
	private static Rectangle dealRotateSize(Rectangle src) {
		double r = Math.sqrt(src.width * src.width + src.height * src.height) / 2;
		double len = 2 * Math.sin(Math.toRadians(ANGLE) / 2) * r;
		double rotate_alpha = (Math.PI - Math.toRadians(ANGLE)) / 2;
		double rotate_alpha_width = Math.atan((double) src.height / src.width);
		double rotate_alpha_height = Math.atan((double) src.width / src.height);
		int len_alpha_width = (int)(len * Math.cos(Math.PI - rotate_alpha - rotate_alpha_width));
		int len_alpha_height = (int)(len * Math.cos(Math.PI - rotate_alpha - rotate_alpha_height));
		int des_width = src.width + len_alpha_width*2;
		int des_height = src.height + len_alpha_height*2;
		return new java.awt.Rectangle(new Dimension(des_width, des_height));
	}
}
