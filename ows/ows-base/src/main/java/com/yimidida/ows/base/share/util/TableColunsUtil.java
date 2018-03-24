package com.yimidida.ows.base.share.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.yimidida.ows.base.share.entity.AccessToken;

public class TableColunsUtil {

	public static void genTableColuns(Class<?> cls) {

		List<Field> fields = new ArrayList<Field>();
		ObjectUtil.getFields(cls, fields);
		StringBuffer str = new StringBuffer("var columns = [	 ");
		for (Field field : fields) {
			str.append("{\r");
			str.append("	field : '" + field.getName() + "', \r");
			str.append("	title : '" + field.getName() + "' \r");
			str.append("	},");
		}
		String str1 = str.substring(0, str.length() - 1);
		str1 = str1 + "]";
		System.out.println(str1.toString());
	}

	public static void main(String[] args) {
		genTableColuns(AccessToken.class);
	}

}
