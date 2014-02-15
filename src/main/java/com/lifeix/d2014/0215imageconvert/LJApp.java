package com.lifeix.d2014.0215imageconvert;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LJApp {
	public static void test(String path, boolean change) throws IOException{
		File f = new File(path);
		String fileName=f.getName();
		String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
		Image image = ImageIO.read(f);
		BufferedImage bufImg = new BufferedImage(image.getWidth(null), image.getHeight(null),BufferedImage.TYPE_INT_RGB);   
        Graphics g = bufImg .createGraphics();   
        g.drawImage(image, 0, 0, null);   
        g.dispose(); 
        javax.imageio.ImageIO.write(rotate90DX(bufImg, change) , prefix, new FileOutputStream(path));
	}
	
	public static BufferedImage rotate90DX(BufferedImage bi, boolean change)
	{
	    int width = bi.getWidth();
	    int height = bi.getHeight();
	    BufferedImage biFlip = new BufferedImage(height, width, bi.getType());
	    for(int i=0; i<width; i++){
	        for(int j=0; j<height; j++){
	        	if(change){
	        		biFlip.setRGB(height-1-j, i, bi.getRGB(i, j));
	        	}else{
	        		biFlip.setRGB(j, width-1-i, bi.getRGB(i, j));
	        	}
	        }
	    }
	    return biFlip;
	}
    public static void main(String[] args) {
    	//true为顺时针，false为逆时针
    	boolean change = true;
    	//图片地址
    	String path = "/home/abc/下载/1.png";
        try {
			test(path, change);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
