package org.hbhk.aili.core.share.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @Description: aili框架核心处理和基本支持
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public class ImageUtil {
	
	private  static Log log = LogFactory.getLog(ImageUtil.class);
	
	public static void resize(InputStream input, int width, int height,
			String path, String name) throws IOException {
		BufferedImage source = ImageIO.read(input);
		Image image = source.getScaledInstance(width, height,
				Image.SCALE_SMOOTH);
		BufferedImage target = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_BGR);
		Graphics g = target.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		InputStream is = getImageStream(target);
		saveFile(is, path, name);
	}

	public static InputStream getImageStream(BufferedImage source) {
		InputStream is = null;
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ImageOutputStream imOut;
		try {
			imOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(source, "png", imOut);
			is = new ByteArrayInputStream(bs.toByteArray());
		} catch (IOException e) {
			log.error("error",e);
		}
		return is;
	}

	public static boolean saveFile(InputStream is, String path,String name) throws IOException {
		log.debug("file info:" + path  + "," + name);
		String uploadFile = path;
		File f = new File(uploadFile);
		if (!f.exists()) {
			f.mkdirs();
		}
		FileUtils.copyInputStreamToFile(is, new File(uploadFile, name));
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		String address="http://192.168.10.50/pkp-pda-itf/obtainPictureServlet?filePath=/app01/ocb/2015-08-04/W0114050403/862369654/3dbdb94b-ac63-4bc6-b4bd-24da9d7ed96b/862369654.webp";
		URL url = new URL(address);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setConnectTimeout(60000);
		urlConnection.setReadTimeout(60000);
		urlConnection.connect();
		InputStream in = urlConnection.getInputStream();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BufferedImage image = ImageIO.read(in);
	    ImageIO.write(image, "png", os); 
	    os.flush();
	    InputStream buffin = new ByteArrayInputStream(os.toByteArray()); 
		resize(buffin, 100, 100, "D:/Documents/Pictures", "1");
	}
}
