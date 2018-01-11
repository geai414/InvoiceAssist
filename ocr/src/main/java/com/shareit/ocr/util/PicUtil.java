package com.shareit.ocr.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder; 

public class PicUtil {

	public static void SaveCode2Image(String code,String fileName) {
		
		@SuppressWarnings("restriction")
		BASE64Decoder  decoder = new BASE64Decoder();
		try {
			
			@SuppressWarnings("restriction")
			byte [] b = decoder.decodeBuffer(code.split(",")[1].toString());
			FileOutputStream image = new FileOutputStream(fileName);
			image.write(b);
			image.flush();
			image.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public static String encodeImage(String filename) throws IOException  
    { 
		InputStream inputStream = null;
	    byte[] data = null;
	    try {
	        inputStream = new FileInputStream(filename);
	        data = new byte[inputStream.available()];
	        inputStream.read(data);
	        inputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // 加密
	    BASE64Encoder encoder = new BASE64Encoder();
	    return "data:image/png;base64," +  encoder.encode(data).replace("\r\n","");
//        return "data:image/png;base64," + encoder.encode(data);//返回Base64编码过的字节数组字符串  
    }  
	
	public static byte[] getCodeData(String base64Pic,String codeInfo,int heightOffset) {
		
		File dir = new File(".\\codeimage\\" );
		String picFileName= "";
		if(!dir.exists()|| !dir.isDirectory()) {
			dir.mkdirs();
		}
		try {
			 picFileName = dir.getCanonicalPath() + File.separator + System.currentTimeMillis() + ".png";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("Base64编码:" + base64Pic);
//		System.out.println("图片路径:" + picFileName);
//		System.out.println("图片信息:" + codeInfo);
		PicUtil.SaveCode2Image(base64Pic,picFileName);
		try {
			PicUtil.AddTxt2Image(picFileName, codeInfo, heightOffset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("图片添加文字出错");
			e.printStackTrace();
		}
		
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(new File(picFileName));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		}
		catch (FileNotFoundException ex1) {
		       ex1.printStackTrace();
	    }
	    catch (IOException ex1) {
	    	ex1.printStackTrace();
	    }
		
		return data;
	}
	public static void AddTxt2Image(String filename,String txt,int heightOffset) throws IOException {
		
		File srcImgFile = new File(filename);//得到文件
        BufferedImage srcImg = ImageIO.read(srcImgFile);
        int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
        int srcImgHeight = srcImg.getHeight(null);//获取图片的高
        int descImgHeight = srcImgHeight + heightOffset; //新图片高度
        
        BufferedImage descImg = new BufferedImage(srcImgWidth,descImgHeight,BufferedImage.TYPE_INT_RGB);
        Graphics2D dg = (Graphics2D) descImg.createGraphics();
        dg.setColor(Color.BLACK);
        dg.fillRect(0, 0, srcImgWidth, descImgHeight);
        dg.drawImage(srcImg, 0, 0, srcImgWidth,srcImgHeight,null);
//        if(txt.contains("蓝色")) {
//        	dg.setColor(Color.BLUE);
//        }else if(txt.contains("红色")) {
//        	dg.setColor(Color.RED);
//        }else if(txt.contains("黄色")) {
//        	dg.setColor(Color.YELLOW);
//        }else {
//        	dg.setColor(Color.BLACK);
//        }
        dg.setColor(Color.WHITE);
        dg.setFont(new Font(dg.getFont().getName(),0,16));
        dg.drawString(txt,0 + 5,srcImgHeight + (descImgHeight-srcImgHeight)/2);
        
        ImageIO.write(descImg, "png",new File(filename));
//        FileOutputStream out = null;  
//        try {
//        	out = new FileOutputStream(filename);    
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);    
//            encoder.encode(descImg);    
//        }
//        finally {
//        	 if(out != null){  
//                 out.close();    
//             } 
//        }
        
	}
}
