/**
 * 
 */
package org.hbhk.aili.i18n.server.support.file.handler.change;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hbhk.aili.i18n.server.support.file.handler.AbstractChangeStringToFile;
import org.hbhk.aili.i18n.server.support.file.handler.extract.ExtractJspChineseStringToPropertyFile;


/**
 * 将jsp文件中的中文替换成<spring:message code=\"" + code + "\"/>
 */
public class ChangeChineseToSpringI18n extends AbstractChangeStringToFile{
	
	
	Pattern p = Pattern.compile(ExtractJspChineseStringToPropertyFile.CHINESE_PATTERN_STRING);
	/*
	 * (non-Javadoc)
	 * @see com.baozun.nebula.i18n.AbstractCharFileHandler#executeLine(java.lang.String)
	 */
	@Override
	protected void executeLine(String line) throws Exception{
		// TODO Auto-generated method stub
		Matcher m = p.matcher(line);
		while (m.find()){
			String value = m.group();
			String code = changeMap.get(value);
			if (code != null){
				line = m.replaceFirst("<spring:message code=\"" + code + "\"/>");
			}else{
				break;
			}
			m = p.matcher(line);
		}
		resultFileWriter.write(line + ENTER_LINE);
	}
}
