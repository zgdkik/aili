/**
 * 
 */
package org.hbhk.aili.i18n.server.support.file.handler.extract;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hbhk.aili.i18n.server.support.file.handler.AbstractExtractStringToFile;

/**
 * 抽取js中的中文，到一个外部文件
 */
public class ExtractJsChineseStringToJsFile extends AbstractExtractStringToFile{
	/**
	 * js中中文的正则表达式，匹配以单引号或双引号开始，然后紧接中文，加任意字符，单引号或双引号结束。
	 * 例如：
	 * 匹配的有：
	 * "确认"
	 * "第1行？"
	 * '操作成功'
	 * 
	 * 	不能匹配的有：
	 *	//保存
	 * "1确认"
	 */
	public static final String CHINESE_PATTERN_STRING = "[\"|']+([\u4E00-\u9FA5]+.*)[\"|']+";

	/**
	 * 验证的map，若在map的key中已经有的中文，则不会提取
	 */
	private Map<String,String> checkMap;

	
	private String headString;

	private Pattern p = Pattern.compile(ExtractJsChineseStringToJsFile.CHINESE_PATTERN_STRING);
	
	
	
	public ExtractJsChineseStringToJsFile(){
	}

	public ExtractJsChineseStringToJsFile(String headString){
		this.headString = headString;
	}

	/*
	 * (non-Javadoc)
	 * @see com.baozun.nebula.i18n.AbstractCharFileHandler#executeLine(java.lang.String)
	 */
	@Override
	protected void executeLine(String line) throws Exception{
		Matcher m = p.matcher(line);
		while (m.find()){
			String value = m.group(1);
			if(checkMap.get(value)==null){
//				"ROLE_NAME":"角色名称",
				toFileWrite.write("\""+headString + "\":\"" + value +"\","+ENTER_LINE);
				checkMap.put(value, headString);
			}
			line = m.replaceFirst("");
			m = p.matcher(line);
		}
	}

	public Map<String, String> getCheckMap(){
		return checkMap;
	}

	public void setCheckMap(Map<String, String> checkMap){
		this.checkMap = checkMap;
	}

	
	public String getHeadString(){
		return headString;
	}

	
	public void setHeadString(String headString){
		this.headString = headString;
	}

}
