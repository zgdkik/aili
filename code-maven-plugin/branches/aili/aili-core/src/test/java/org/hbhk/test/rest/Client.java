package org.hbhk.test.rest;

import org.springframework.web.client.RestTemplate;

public class Client {

	
	public static void main(String[] args) {  
	    RestTemplate template = new RestTemplate();
	    template.getForEntity("", String.class);
	  
	}  
}
