package org.hbhk.maikkr.core.shared.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReSizeImageUtil {
	
	private  static Logger log = LoggerFactory.getLogger(ReSizeImageUtil.class);
	
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
	
	public static void main(String[] args) throws IOException {
		File file = new File(
				"C:/Users/Administrator/Desktop/mikkr-html/images/home__nologin_/u36.jpg");
		InputStream input = new FileInputStream(file);
		resize(input, 50, 50,
				"C:/Users/Administrator/Desktop/mikkr-html/images/hbhk/123/",
				"test2.png");
	}
}
