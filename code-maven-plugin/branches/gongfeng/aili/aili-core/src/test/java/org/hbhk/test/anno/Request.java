package org.hbhk.test.anno;

import java.util.UUID;

public class Request {
	public String getParameter(String name){
		return "hello"+name;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			System.out.println(UUID.randomUUID().toString());
		}
		
	}
}
