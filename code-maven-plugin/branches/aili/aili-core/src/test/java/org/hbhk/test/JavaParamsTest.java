package org.hbhk.test;

import java.util.Arrays;

import org.hbhk.aili.core.share.util.JavaParamsUtil;

public class JavaParamsTest {
	   public static void main(String[] args1) throws Exception {  
	        // 匹配静态方法  
	        String[] paramNames = JavaParamsUtil.getMethodParamNames(JavaParamsUtil.class, "main", String[].class);  
	        System.out.println(Arrays.toString(paramNames));  
	        // 匹配实例方法  
	        paramNames = JavaParamsUtil.getMethodParamNames(JavaParamsUtil.class, "foo", String.class);  
	        System.out.println(Arrays.toString(paramNames));  
	        // 自由匹配任一个重名方法  
	        paramNames = JavaParamsUtil.getMethodParamNames(JavaParamsUtil.class, "getMethodParamNames");  
	        System.out.println(Arrays.toString(paramNames));  
	        // 匹配特定签名的方法  
	        paramNames = JavaParamsUtil.getMethodParamNames(JavaParamsUtil.class, "getMethodParamNames", Class.class, String.class);  
	        System.out.println(Arrays.toString(paramNames));  
	    }  
}
