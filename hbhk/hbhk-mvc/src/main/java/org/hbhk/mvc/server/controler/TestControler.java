package org.hbhk.mvc.server.controler;

import org.hbhk.mvc.server.mapping.RequestMapping;

@RequestMapping("test")
public class TestControler {

	@RequestMapping("test")
	public void test(String p1, String p2) {
		System.out.println("p1:" + p1 + "  p2:" + p2);
	}
}
