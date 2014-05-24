package org.hbhk.aili.ioc.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.hbhk.ioc.convertor.IOCBeansConvertor;
import org.hbhk.ioc.domain.Beans;
import org.junit.Test;

public class IOCBeansConvertorTest {

	@Test
	public void testbeansXml() {
		org.hbhk.ioc.util.FileScanUtil fsc = new 		org.hbhk.ioc.util.FileScanUtil();

		String dirPath = "org/hbhk/aili/";
		List<String> xmls = null;
		try {
			xmls = fsc.scanBeansXml(dirPath, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String string : xmls) {
			System.out.println(string);
			IOCBeansConvertor ioc = new IOCBeansConvertor();
			try {
				Beans b =ioc.toMessage(string);
				System.out.println(b.getBeans().get(0).getId());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
}
