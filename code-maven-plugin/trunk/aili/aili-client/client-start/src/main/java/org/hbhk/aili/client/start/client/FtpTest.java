package org.hbhk.aili.client.start.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

	
public class FtpTest {
	   public static void main(String[] args) {
	         //testUpload();
	         testDownload();
	     }
	 
	    /**
	      * FTP上传单个文件测试
	      */
	     public static void testUpload() {
	         FTPClient ftpClient = new FTPClient();
	         FileInputStream fis = null;
	 
	        try {
	             ftpClient.connect("192.168.4.201",21);
	             ftpClient.login("dpfoss", "foss*ftp");
	 
	            File srcFile = new File("路径+本地文件名");
	             fis = new FileInputStream(srcFile);
	             
	            //enterLocalPassiveMode：设置客户端PASV模式 (客户端主动连服务器端；端口用20)
	             //enterLocalActiveMode：设置客户端PORT模式  (服务器端连客户端；随机打开一个高端端口(端口号大于1024))
	             ftpClient.enterLocalPassiveMode();
	             //ftpClient.enterLocalActiveMode();
	             
	            //设置上传目录
	             ftpClient.changeWorkingDirectory("/");
	             ftpClient.setBufferSize(1024);
	             ftpClient.setControlEncoding("GBK");
	             //设置文件类型（二进制）
	             ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
	             ftpClient.storeFile("远程文件名", fis);
	         } catch (IOException e) {
	             e.printStackTrace();
	         } finally {
	        	 
	             try {
	            	 fis.close();
	             
	                 ftpClient.disconnect();
	             } catch (IOException e) {
	                 e.printStackTrace();
	                 throw new RuntimeException("关闭FTP 连接发生异常！", e);
	             }
	         }
	     }
	 

	    /**
	      * FTP下载单个文件测试
	      */
	     public static void testDownload() {
	         FTPClient ftpClient = new FTPClient();
	         FileOutputStream fos = null;
	 
	        try {
	        	 ftpClient.connect("192.168.4.201",21);
	             ftpClient.login("dpfoss", "foss*ftp");
	             
	             ftpClient.enterLocalPassiveMode();
	             
	            //远程文件名
	             String remoteFileName = "/appHome/launcher.properties";
	             
	            //本地文件
	             fos = new FileOutputStream("d:/launcher.properties1");
	 
	            ftpClient.setBufferSize(1024);
	             //设置文件类型（二进制）
	             ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
	             ftpClient.retrieveFile(remoteFileName, fos);
	         } catch (IOException e) {
	             e.printStackTrace();
	             throw new RuntimeException("FTP客户端出错！", e);
	         } finally {
	        	 
	             try {
	            	 fos.close();
	                 ftpClient.disconnect();
	             } catch (IOException e) {
	                 e.printStackTrace();
	                 throw new RuntimeException("关闭FTP 连接发生异常！", e);
	             }
	         }
	     }
	 
}