package org.hbhk.aili.client.core.commons.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 *图片工具类，用来获取指定存放位置的图片，图片地址存放位置为org/hbhk/aili/client/META-INF/images
 */
public  final class ImageUtil {
	// 图片目录
	private static final String IMAGE_BASENAME = "org/hbhk/aili/client/META-INF/images";
	
	// 缓存的图片资源信息
	private static Map<ClassLoader, Map<String, ImageIcon>> clzMapIcons = new HashMap<ClassLoader, Map<String,ImageIcon>>();
	
	
	private ImageUtil(){
		
	}
	
	/**
	 * 以Class为参数搜索指定路径下指定名字的图片，返回ImageIcon对象。
	 * getImageIcon
	 * @param cls
	 * @param imagePath
	 * @return
	 * @return ImageIcon
	 * @since:0.6
	 */
	@SuppressWarnings("rawtypes")
	public static ImageIcon getImageIcon(Class cls, String imagePath) {
		return getImageIcon(cls.getClassLoader(), imagePath);
		
	}
	
	/**
	 * 以ClassLoader为参数查找图片。当在指定位置找不到图片的时候，会返回一个全黑的图片。
	 * 此图片宽和高都是16像素。
	 * getImageIcon
	 * @param classLoader
	 * @param imagePath
	 * @return
	 * @return ImageIcon
	 * @since:0.6
	 */
	public static ImageIcon getImageIcon(ClassLoader classLoader, String imagePath){
		Map<String, ImageIcon> iconMap = clzMapIcons.get(classLoader);
		ImageIcon imageIcon = null;
		if (iconMap != null) {
			imageIcon = iconMap.get(imagePath);
			if (imageIcon != null) {
				return imageIcon;
			}
		} else {
			iconMap = new HashMap<String, ImageIcon>();
		}
		
		try {
			URL url = classLoader.getResource(IMAGE_BASENAME+"/"+imagePath);
			if (url!=null) {
				imageIcon = new ImageIcon(url);
			}
		} catch (Exception e) {
		}
		if (imageIcon==null) {
			imageIcon = new ImageIcon(new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB));
		}
		iconMap.put(imagePath, imageIcon);
		clzMapIcons.put(classLoader, iconMap);
		return imageIcon;
	}
	
	/**
	 * 通过ClassLoader加载指定位置的图片，返回Image对象。
	 * getImage
	 * @param classLoader
	 * @param imagePath
	 * @return
	 * @return Image
	 * @since:0.6
	 */
	public static Image getImage(ClassLoader classLoader,String imagePath){
		return getImageIcon(classLoader, imagePath).getImage();
	}
	
	/**
	 * 以Class为参数加载指定位置的图片，返回Image对象。
	 * getImage
	 * @param cls
	 * @param imagePath
	 * @return
	 * @return Image
	 * @since:0.6
	 */
	@SuppressWarnings("rawtypes")
	public static Image getImage(Class cls,String imagePath){
		return getImageIcon(cls, imagePath).getImage();
	}
}