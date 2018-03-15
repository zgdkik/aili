package org.hbhk.aili.client.start.util;

import java.io.InputStream;

/**
 * 读取classpath 下的资源文件
 */
public class ClassPathResourceUtil {
    
    private ClassLoader clzloader = null;
    
    public ClassPathResourceUtil(){
    }
    
    public ClassPathResourceUtil(ClassLoader classloader){
    	this.clzloader = classloader;
    }
    
    public InputStream getInputStream(String pPath){
        InputStream is = null;
        try{
        	if(this.clzloader!=null){
        		is = this.clzloader.getResourceAsStream(pPath);
        	}
        	else {
        	
	            is = getClass().getResourceAsStream(pPath);
	            if(is==null){
	                is = (getClass().getClassLoader()).getResourceAsStream(pPath);
	            }
        	}
        }catch(Exception e){
            is = null;
        }
        return is;
    }
}