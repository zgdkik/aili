package org.hbhk.test;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.io.FileUtils;

public class ImageUtilTest extends JFrame {

	
	public static void main(String[] args) throws Exception {
		new ImageUtilTest();
	}
	
	public ImageUtilTest() throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				setVisible(true);
				setSize(100, 100);
				String address="http://192.168.10.50/pkp-pda-itf/obtainPictureServlet?filePath=/app01/ocb/2015-08-04/W0114050403/862369654/3dbdb94b-ac63-4bc6-b4bd-24da9d7ed96b/862369654.webp";
				//address ="https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1164747503,1799665606&fm=58";
				queryImage(address);
			}
		});
	}
	
  	public byte[] queryImage(String filePath) {
  		ByteArrayOutputStream os = new ByteArrayOutputStream();
  		InputStream in = null;
  		try {  
  		    URL url = new URL(filePath);  
  		    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
  		    urlConnection.setConnectTimeout(60000);
  		    urlConnection.setReadTimeout(60000);
  		    urlConnection.connect();
  		    in = urlConnection.getInputStream();
  		    File  file = new File("");
  		    
  		    FileOutputStream  fos = new FileOutputStream(file);
  		    fos.write(readInputStream(in));
  		    
  		    BufferedInputStream bis = new BufferedInputStream(url.openStream());
  		    BufferedImage image = ImageIO.read(bis);
  		    ImageIO.write(image, "png", os); 
  		    os.flush();
  		    return os.toByteArray();
  	    } catch (Exception e) {  
  	    	e.printStackTrace();
  	        return null;
  	    } finally {
  	    	 if (null != in) {
  		    	 try {
  					in.close();
  					in = null;
  				} catch (Exception e) {
  					e.printStackTrace();
  				}
  	    	 }
  	    	 
  	    	 if (null != os) {
  	    		 try {
  					os.close();
  					os = null;
  				} catch (Exception e) {
  					e.printStackTrace();
  				}
  	    		 
  	    	 }
  	    }
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
