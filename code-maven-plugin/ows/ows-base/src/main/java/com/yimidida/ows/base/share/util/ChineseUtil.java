package com.yimidida.ows.base.share.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChineseUtil {

	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}
}
