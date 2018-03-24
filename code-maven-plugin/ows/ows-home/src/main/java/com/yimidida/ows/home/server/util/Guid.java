package com.yimidida.ows.home.server.util;

import java.util.UUID;

public class Guid {

	public static  String getGuid(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().toUpperCase();
	}
}
