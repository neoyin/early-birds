package com.lifeix.d2014.0215imageconvert;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class AWJ_RotateImage {
	public static BufferedImage Rotate(Image src, int angle) {
		int src_width = src.getWidth(null);
		int src_height = src.getHeight(null);
		//计算新图片的size
		Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(
				src_width, src_height)), angle);

		BufferedImage res = null;
		res = new BufferedImage(rect_des.width, rect_des.height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = res.createGraphics();
		g2.translate((rect_des.width - src_width) / 2,
				(rect_des.height - src_height) / 2);
		g2.rotate(Math.toRadians(angle), src_width / 2, src_height / 2);

		g2.drawImage(src, null, null);
		return res;
	}

	public static Rectangle CalcRotatedSize(Rectangle src, int angle) {
		if (angle >= 90) {
			if(angle / 90 % 2 == 1){
				System.out.println(angle / 90 % 2);
				int temp = src.height;
				src.height = src.width;
				src.width = temp;
			}
			angle = angle % 90;
		}

		double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
		double len = 2 * Math.sin(Math.toRadians(angle) / 2) * r;
		double angle_alpha = (Math.PI - Math.toRadians(angle)) / 2;
		double angle_dalta_width = Math.atan((double) src.height / src.width);
		double angle_dalta_height = Math.atan((double) src.width / src.height);

		int len_dalta_width = (int) (len * Math.cos(Math.PI - angle_alpha
				- angle_dalta_width));
		int len_dalta_height = (int) (len * Math.cos(Math.PI - angle_alpha
				- angle_dalta_height));
		int des_width = src.width + len_dalta_width * 2;
		int des_height = src.height + len_dalta_height * 2;
		return new java.awt.Rectangle(new Dimension(des_width, des_height));
	}

	public static void main(String[] args) throws IOException{
		BufferedImage src = ImageIO.read(new File("/tmp/image.jpg"));
		BufferedImage des = AWJ_RotateImage.Rotate(src, 90);
		ImageIO.write(des, "jpg", new File("/tmp/image2.jpg"));
	}
}
