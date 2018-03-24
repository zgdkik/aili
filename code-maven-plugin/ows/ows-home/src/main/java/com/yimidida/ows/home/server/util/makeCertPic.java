package com.yimidida.ows.home.server.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * @author 生成验证码图片
 */

public class makeCertPic {
	// 验证码图片中可以出现的字符集，可根据需要修改
	private static char mapTable[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9' };

	/**
	 * 功能:生成彩色验证码图片 参数width为生成图片的宽度,参数height为生成图片的高度,参数os为页面的输出流
	 */
	public String getCertPic(OutputStream os) {
		Font font = new Font("Atlantic Inline", Font.LAYOUT_NO_LIMIT_CONTEXT, 23);
		int stringLength = 4;
		int height = (int) (font.getSize() * 1.6);
		int width = (int) (stringLength * font.getSize() * 1);
		if (width <= 0)
			width = 100;
		if (height <= 0)
			height = 36;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 设定背景色
		g.setColor(new Color(255,255,255));
		//g.setColor(new Color(201,227,224));
		g.fillRect(0, 0, width, height);
		// 画边框
		//g.setColor(randomColor(234234));
		g.setColor(new Color(204,204,204));
		g.drawRect(0, 0, width - 1, height - 1);
		// 取随机产生的认证码
		String strEnsure = "";
		// 4代表4位验证码,如果要生成更多位的认证码,则加大数值
		for (int i = 0; i < 4; ++i) {
			strEnsure += mapTable[(int) (mapTable.length * Math.random())];
		}
		strEnsure=strEnsure.toUpperCase();
		// 　　将认证码显示到图像中,如果要生成更多位的认证码,增加drawString语句
		g.setColor(Color.black);
		g.setFont(font);
		// 随机产生10个干扰点
		Random rand = new Random();
		int lineCount=rand.nextInt(10);
		for (int i = 0; i < lineCount; i++) {
			g.setColor(randomColor(i+255));
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			int x2 = rand.nextInt(width);
			int y2 = rand.nextInt(height);
			// g.drawOval(x, y, 1, 1);
			g.drawLine(x, y, x2, y2);
		}
		g.setColor(randomColor(255));
		int x=0;
		String str = strEnsure.substring(0, 1);
		g.drawString(str, x+=7+new Random().nextInt(10), new Random().nextInt(3)+24);
		g.setColor(randomColor(255));
		str = strEnsure.substring(1, 2);
		g.drawString(str, x+=9+new Random().nextInt(10), new Random().nextInt(3)+25);
		g.setColor(randomColor(255));
		str = strEnsure.substring(2, 3);
		g.drawString(str, x+=11+new Random().nextInt(10), new Random().nextInt(3)+24);
		g.setColor(randomColor(255));
		str = strEnsure.substring(3, 4);
		g.drawString(str, x+=13+new Random().nextInt(10), new Random().nextInt(3)+25);
		try {
			// 输出图像到页面
			ImageIO.write(image, "PNG",os);
			os.close();
		} catch (IOException e) {
			return "";
		}
		// 释放图形上下文
		g.dispose();
		return strEnsure;
	}

	static Color randomColor(int seed) {
		return new Color(new Random().nextInt(seed) % 255,
				new Random().nextInt(seed) % 255,
				new Random().nextInt(seed) % 255);
	}

	
}
