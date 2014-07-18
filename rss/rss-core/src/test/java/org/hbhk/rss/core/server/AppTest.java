package org.hbhk.rss.core.server;

import java.io.StringWriter;
import java.io.Writer;

import org.hbhk.aili.support.server.httpclient.HttpClientUtil;
import org.hbhk.aili.support.server.httpclient.ResponseContent;
import org.hbhk.rss.core.shared.vo.WeatherVo;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

//// 回复一条消息
// Data4Item d1 = new Data4Item("hbhk", "测试描述",
// "http://cms.yl-blog.com/themes/blue/images/logo.png",
// "cms.yl-blog.com");
// Data4Item d2 = new Data4Item(
// "hbhk",
// "测试描述",
// "http://www.yl-blog.com/template/ylblog/images/logo.png",
// "www.yl-blog.com");
// Msg4ImageText mit = new Msg4ImageText();
// mit.setFromUserName(msg.getToUserName());
// mit.setToUserName(msg.getFromUserName());
// mit.setCreateTime(msg.getCreateTime());
// mit.addItem(d1);
// mit.addItem(d2);
// session.callback(mit);
public class AppTest {
	private static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws Exception {
		String url = "http://api.map.baidu.com/telematics/v3/weather?output=json&ak=1rENYOIqG1RIMwnfH5uHS1o9";
		ResponseContent<String> responseContent = HttpClientUtil.post(url)
				.param("location", "北京").send();
		String ss = responseContent.getResult();
		System.out.println(ss);
		Writer w = new StringWriter();
		w.append(ss);
		WeatherVo vo = new WeatherVo();
		mapper.writeValue(w, vo);
		vo = mapper.readValue(ss, WeatherVo.class);
		System.out.println(vo.getDate());
		;

		ResponseContent<WeatherVo> responseContent1 = HttpClientUtil.post(url)
				.returnJson(WeatherVo.class);
		System.out.println(responseContent1.getResult());
	}

	@Test
	public void testmenu() {
		String menu = "1->20->30";
		menu = menu.substring(menu.lastIndexOf("->") + "->".length(),
				menu.length());
		System.out.println(menu);
	}
}