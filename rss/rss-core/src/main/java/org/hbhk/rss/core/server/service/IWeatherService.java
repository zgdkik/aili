package org.hbhk.rss.core.server.service;

import org.hbhk.rss.core.shared.vo.WeatherVo;
import org.hbhk.rss.weixinapi.server.security.DefaultSession;

public interface IWeatherService {
	
	void  getBaiduWeatherToXml(String cityName,DefaultSession session);
	
	WeatherVo getBaiduWeather(String cityName);
}
