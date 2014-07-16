package org.hbhk.rss.core.server;

import java.io.StringWriter;
import java.io.Writer;

import org.hbhk.aili.support.server.httpclient.HttpClientUtil;
import org.hbhk.aili.support.server.httpclient.ResponseContent;
import org.hbhk.rss.core.shared.vo.WeatherVo;

import com.fasterxml.jackson.databind.ObjectMapper;


public class AppTest{
	private static ObjectMapper mapper = new ObjectMapper();
	public static void main(String[] args) throws Exception {
		String url ="http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=1rENYOIqG1RIMwnfH5uHS1o9";
		ResponseContent<String>  responseContent= HttpClientUtil.post(url).send();
		String ss = responseContent.getResult();
		System.out.println(ss);
		Writer w = new StringWriter();
		w.append(ss);
		WeatherVo vo = new WeatherVo();
		mapper.writeValue(w, vo);
		vo = mapper.readValue(ss, WeatherVo.class);
		System.out.println(vo.getDate());;
		
		
		ResponseContent<WeatherVo>  responseContent1= HttpClientUtil.post(url).returnJson(WeatherVo.class);
		System.out.println(responseContent1.getResult());
	}
}