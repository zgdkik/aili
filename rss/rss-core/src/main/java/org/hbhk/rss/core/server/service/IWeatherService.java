package org.hbhk.rss.core.server.service;

import org.hbhk.rss.core.shared.vo.WeatherVo;
import org.hbhk.rss.weixinapi.server.msg.Msg4Text;

public interface IWeatherService {
	
	Msg4Text  getBaiduWeatherToXml(String cityName);
	
	WeatherVo getBaiduWeather(String cityName);
}
