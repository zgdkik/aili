package org.hbhk.aili.rpc.server.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.experimental.theories.ParametersSuppliedBy;

public class AppTest {

	public static void main(String[] args) {
	
		Pattern pattern = Pattern.compile("[\\-._0-9a-zA-Z]+");
	    Matcher matcher = pattern.matcher("wmsHeaderExchanger");
	    if(!matcher.matches()) {
	    	System.out.println("error");
	    }else{
	    	System.out.println("s");
	    }
	}

}