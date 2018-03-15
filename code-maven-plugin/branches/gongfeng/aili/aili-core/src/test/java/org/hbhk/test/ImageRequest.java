package org.hbhk.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageRequest {  
    /** 
     * @param args 
     */  
    public static void main(String[] args) throws Exception {  
    	
    	String address="http://192.168.10.50/pkp-pda-itf/obtainPictureServlet?filePath=/app01/ocb/2015-08-04/W0114050403/862369654/3dbdb94b-ac63-4bc6-b4bd-24da9d7ed96b/862369654.webp";
        //new一个URL对象  
        URL url = new URL(address);  
        //打开链接  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        //设置请求方式为"GET"  
        conn.setRequestMethod("GET");  
        //超时响应时间为5秒  
        conn.setConnectTimeout(5 * 1000);  
        //通过输入流获取图片数据  
        InputStream inStream = conn.getInputStream();  
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
        byte[] data = readInputStream(inStream);  
        //new一个文件对象用来保存图片，默认保存当前工程根目录  
        File imageFile = new File("D:/Documents/Pictures/BeautyGirl.jpg");  
        
    	BufferedImage image = ImageIO.read(new ByteArrayInputStream(data));
    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    ImageIO.write(image, "png", os); 
	    
        //创建输出流  
        FileOutputStream outStream = new FileOutputStream(imageFile);  
        //写入数据  
        outStream.write(data);  
        //关闭输出流  
        outStream.close();  
    }  
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
    }  
}  
