package org.hbhk.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestfulTest {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		RestfulEntity responseType = new RestfulEntity();
		responseType.setName("hbhk");
		responseType.setDate(new Date());
		List<RestfulDetailEntity> list = new ArrayList<RestfulDetailEntity>();
		for (int i = 0; i < 5; i++) {
			RestfulDetailEntity responseType1 = new RestfulDetailEntity();
			responseType1.setName("hbhk"+i);
			responseType1.setDate(new Date());
			list.add(responseType1);
		}
		responseType.setDetails(list);
		responseType.setNum(new BigDecimal(100));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RestfulEntity> request = new HttpEntity<RestfulEntity>(responseType, headers);
		ResponseEntity<RestfulEntity> res = restTemplate.postForEntity("http://10.224.68.144:8080/foss/restful/waybill/sign/getWaybillSign", request, RestfulEntity.class);
		System.out.println(res.getBody().getName());
		
	}
}
