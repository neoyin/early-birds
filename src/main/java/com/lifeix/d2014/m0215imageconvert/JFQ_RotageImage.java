package com.lifeix.d2014.0215imageconvert;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JFQ_RotageImage {

	public static void main(String[] args) throws IOException {
		String path = "/home/abc/下载/3.png";
		File file = new File(path);
		String fileName=file.getName();
		String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
		if(!file.exists()) {
			throw new RuntimeException("no path " + path);
		}
		BufferedImage image = ImageIO.read(file);
		
		int iw = image.getWidth();
        int ih = image.getHeight(); 
        int w = 0;  
        int h = 0;  
        int x = 0;  
        int y = 0;  
        w = ih;  
        h = iw;  
        x = (w / 2) - (iw / 2);//确定原点坐标  
        y = (h / 2) - (ih / 2);  
        BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());  
          
        AffineTransform at = new AffineTransform();  
        at.rotate(Math.toRadians(90), w / 2, h / 2); 
        at.translate(x, y);  
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);  
        op.filter(image, rotatedImage);  
        image = rotatedImage;  
          
        ImageIO.write(image, prefix, new File(file.getCanonicalPath()+"_new_"+file.getName()));  
	}

}
