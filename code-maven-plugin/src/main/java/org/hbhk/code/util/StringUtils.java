package org.hbhk.code.util;

public class StringUtils {
	
	/**
	 * 把带下划线的表名转成类名
	 * @param tableName
	 * @return
	 */
	public static String generateClassname(String tableName) {
		if(tableName == null || "".equals(tableName)){
			return tableName;
		}
		if (tableName.toUpperCase().startsWith(TablePreReplacement.T_.name())) {
			tableName = TablePreReplacement.T_.replaceEmpty(tableName);
		}
		
		String name = "";
		String[] names = tableName.split("_");
		for (int i = 0; i < names.length; i++) {
			name += capitalize(names[i].toLowerCase());
		}
		return name;
	}
	
	/**
	 * 首字符大写，实现方式来自apache commons-lang3
	 * @param str
	 * @return
	 */
    public static String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuilder(strLen)
            .append(Character.toTitleCase(str.charAt(0)))
            .append(str.substring(1))
            .toString();
    }
    
    public static String uncapitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuilder(strLen)
            .append(Character.toLowerCase(str.charAt(0)))
            .append(str.substring(1))
            .toString();
    }
}
