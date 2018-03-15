package org.hbhk.aili.rpc.server.thrift;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.springframework.util.StringUtils;

public class ThriftServletProxy extends HttpServlet {  
	  
	  
	        @SuppressWarnings({ "rawtypes", "unchecked" })  
	    public ThriftServletProxy(String serviceInterface, String serviceIface,  
	            Object serviceImplObject) throws Exception {  
	        super();  
	        Class Processor = Class.forName(serviceInterface + "$Processor");  
	        Class Iface = Class  
	                .forName(StringUtils.hasText(serviceIface) ? serviceIface  
	                        : serviceInterface + "$Iface");  
	        Constructor con = Processor.getConstructor(Iface);  
	        TProcessor processor = (TProcessor) con.newInstance(serviceImplObject);  
	  
//	        this.processor = processor;  
//	        this.inProtocolFactory = new TCompactProtocol.Factory();  
//	        this.outProtocolFactory = new TCompactProtocol.Factory();  
//	        this.customHeaders = new ArrayList<Map.Entry<String, String>>();  
	  
	    }  
	      
	    public ThriftServletProxy(String serviceInterface,  
	            Object serviceImplObject) throws Exception {  
	        this(serviceInterface,null,serviceImplObject);  
	  
	    }  
}