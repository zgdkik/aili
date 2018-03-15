package org.hbhk.aili.client.core.commons.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 
 * <p>Title: ImageFactory</p>
 * <p>Description: 图片资源门面</p>
 *
 */
public final class ImageFactory {
	/**
	 * 
	 * <p>Title: ImageFactory</p>
	 * <p>Description: 构造方法</p>
	 */
	private ImageFactory() {}
	
	// 图片目录
	private static final String DEFAULT_DIRECTORY = "com/deppon/foss/META-INF/images/";

	private static String directory = DEFAULT_DIRECTORY;

	/**
	 * 设置图片目录(ClassPath中)
	 * @param directory 图片目录
	 */
	public static void setDirectory(String directory) {
		if (directory != null) {
			directory = directory.replace('\\', '/');
			if (! directory.endsWith("/")){
				directory = directory + "/";
			}
				
			ImageFactory.directory = directory;
		}
	}

	/**
	 * 获取类路径图片
	 * @param name 图片名称
	 * @return 图片
	 */
	public static Image getImage(String name) {
		try {
			return ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream(directory + name));
		} catch (IOException e) { // 用全黑图片替代
			return new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		}
	}

	/**
	 * 获取类路径图标
	 * @param name 图标名称
	 * @return 图标
	 */
	public static ImageIcon getIcon(String name) {
		return new ImageIcon(getImage(name));
	}

}
