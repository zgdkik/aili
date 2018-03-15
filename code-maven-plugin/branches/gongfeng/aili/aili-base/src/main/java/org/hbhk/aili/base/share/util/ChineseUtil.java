package org.hbhk.aili.base.share.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ChineseUtil {

	public static boolean isContainChinese(String str) {
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}
}
