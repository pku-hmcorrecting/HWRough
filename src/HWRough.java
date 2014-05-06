import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


public class HWRough{
	/*
	 * @author: Yiming
	 * @Parameter: Filename of the JPG version of the HomeWork
	 * */
	public static void makeRough(String filename) throws IOException{
		File imageFile = new File(filename);  //image.jpegͼƬҪ�����ļ���ͬһĿ¼��
		BufferedImage bi = null;
		BufferedImage bi_new = null;
		try {
			bi = ImageIO.read(imageFile);
			bi_new = ImageIO.read(imageFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/** 
         * �õ�ͼƬ�ĳ��� 
         */  
        int width = bi.getWidth();  
        int height = bi.getHeight();  
        int minx = bi.getMinX();  
        int miny = bi.getMinY(); 
        System.out.println("Precessing: " + imageFile.getName());
        int[] rgb = new int[3];
        for (int i = minx; i < width; i++) {  
            for (int j = miny; j < height; j++) { 
                /** 
                 * �õ�ָ�����أ�i,j)�ϵ�RGBֵ�� 
                 */  
                int pixel = bi.getRGB(i, j);  
                /** 
                 * �ֱ����λ�����õ� r g b�ϵ�ֵ 
                 */  
                rgb[0] = (pixel & 0xff0000) >> 16;  
                rgb[1] = (pixel & 0xff00) >> 8;  
                rgb[2] = (pixel & 0xff);  
                
                int roughness = 2;
                
                if(rgb[0]<70 && rgb[1]<70 && rgb[2]<70){  
                    if (i - minx >= roughness && width - i >= roughness 
                    		&& j - miny >= roughness && height - j >= roughness) {
                    	for (int x = i - roughness; x < i + roughness; x++) {
                    		for (int y = j - roughness; y < j + roughness; y++) {
                                bi_new.setRGB(x, y, 0x0);
							}
						}
					}  
                }  
                  
            }  
        }
        /** 
         * ��������󱣴浽���ļ��� 
         */  
        FileOutputStream ops = new FileOutputStream(new File("2.jpg"));  
        ImageIO.write(bi_new,"jpg", ops);  
        ops.flush();  
        ops.close();
	}
}
