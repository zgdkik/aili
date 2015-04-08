package org.aili.i18n;

import org.hbhk.aili.i18n.server.support.I18nJspMain;
import org.junit.Test;

public class I18nJspMainTest {
	/**
	 * 提取的中文存放的临时文件名,一般不需要修改
	 */
	public static final String TEMP_FILE	= "temp.properties";
	
	/**
	 * 工作目录。临时的属性文件和备份文件会存在此目录
	 */
	public static final String WORK_PATH = "d:/work/";
	/**
	 * 备份的文件名,一般不需要修改
	 */
	public static final String BAK_DIR = "bak/";
	/**
	 * 需要进行国际化的目录或文件
	 */
	public static final String I18N_FILE = "src/test/resources/pages/editRole.jsp";
	
	/**
	 * 已经有的国际化属性文件，程序会验证在国际化属性文件中已经有的中文，不进行重复提取，一般不需要修改
	 * 
	 */
	private static final String	PROPER_FILE = "i18n/message/aili";
	/**
	 * 生成的临时属性文件的前缀
	 * 例如：
	 * HEAD_STRING = "role.";
	 * 那么生成的临时属性文件等号前面全部有  role.
	 * 
	 * 	role.=无标题文档
	 *	role.=修改
	 * 
	 */
	public static final String HEAD_STRING = "aili.i18n.";
	
	
	@Test
	public void collectChinese() throws Exception {
		I18nJspMain.getInstance(PROPER_FILE).collectChinese(I18N_FILE,WORK_PATH,TEMP_FILE,HEAD_STRING);
	}
	@Test
	public void pushCodeTojsp() throws Exception {
		I18nJspMain.getInstance(PROPER_FILE).pushCodeTojsp(I18N_FILE,WORK_PATH,TEMP_FILE,BAK_DIR);
	}
}
