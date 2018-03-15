package org.hbhk.aili.lock.server;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import org.hbhk.aili.map.share.entity.Entity;
import org.hbhk.aili.map.share.entity.ResultEntity;
import org.hbhk.aili.map.share.entity.Track;
import org.hbhk.aili.map.share.http.RestfulClient;
import org.junit.Test;

public class AppTest {

	static AtomicInteger number = new AtomicInteger(0);

	public static void main(String[] args) {

		String url = "http://api.map.baidu.com/trace/v2/entity/add";
		Entity entity = new Entity();
		entity.setEntity_name("hbhk");
		ResultEntity resultEntity = RestfulClient.post(url, entity);

		System.out.println(resultEntity);
	}

	// 31.1783580000,121.2792850000

	@Test
	public void addTrack() {
		Track t = new Track();
		t.setEntity_name("hbhk");
		t.setCoord_type(1);
		t.setLatitude(30.1783580000);
		t.setLongitude(113.2792850000);
		t.setLoc_time(Calendar.getInstance().getTimeInMillis() / 1000);
		String url = "http://api.map.baidu.com/trace/v2/track/addpoint";
		ResultEntity resultEntity = RestfulClient.post(url, t);

		System.out.println(resultEntity);
	}

}