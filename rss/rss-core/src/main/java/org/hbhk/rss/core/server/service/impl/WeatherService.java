package org.hbhk.rss.core.server.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.hbhk.aili.support.server.httpclient.HttpClientUtil;
import org.hbhk.aili.support.server.httpclient.ResponseContent;
import org.hbhk.aili.support.server.httpclient.exception.ClientException;
import org.hbhk.aili.support.server.httpclient.exception.ResponseException;
import org.hbhk.rss.core.server.service.IWeatherService;
import org.hbhk.rss.core.shared.vo.Index;
import org.hbhk.rss.core.shared.vo.WeatherVo;
import org.hbhk.rss.weixinapi.server.msg.Msg4Text;
import org.springframework.stereotype.Service;
@Service
public class WeatherService implements IWeatherService {
	// ak=1rENYOIqG1RIMwnfH5uHS1o9
	// http://developer.baidu.com/map/carapi-7.htm
	private Log log = LogFactory.getLog(getClass());
	private final String url = "http://api.map.baidu.com/telematics/v3/weather?output=json&ak=1rENYOIqG1RIMwnfH5uHS1o9";

	public WeatherVo getBaiduWeather(String cityName) {
		try {
			ResponseContent<WeatherVo> responseContent = HttpClientUtil
					.post(url).param("location", cityName)
					.returnJson(WeatherVo.class);
			if (responseContent.getStatus() == HttpStatus.SC_OK) {
				return responseContent.getResult();
			}
		} catch (ResponseException e) {
			log.error("get baidu weather fial", e);
		} catch (ClientException e) {
			log.error("get baidu weather fial", e);
		}
		return null;
	}

	public Msg4Text getBaiduWeatherToXml(String cityName) {
		 WeatherVo weatherVo = getBaiduWeather(cityName) ;
		 Msg4Text imageText = new Msg4Text();
		 if(weatherVo!=null){
			 List<Index> indexs= weatherVo.getResults().get(0).getIndex();
			 StringBuilder sb = new StringBuilder();
			 String name = weatherVo.getResults().get(0).getCurrentCity();
			 sb.append("城市:"+name+"\n");
			 sb.append("时间:"+weatherVo.getDate()+"\n");
			 sb.append("温度:"+weatherVo.getResults().get(0).getWeather_data().get(0).getTemperature()+"\n");
			 sb.append("指数说明:\n");
			 for (int i = 0; i < indexs.size(); i++) {
				 Index index = indexs.get(i);
				 sb.append(index.getTipt()+":"+index.getDes()+"\n");
			 }
			 imageText.setContent(sb.toString());
			return imageText;
		 }
		 imageText.setContent( "未查询到城市:"+cityName+"天气预报");
		return imageText;
	}

}
