package org.hbhk.aili.weixing.server.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.aili.weixing.server.service.CoreService;
import org.hbhk.aili.weixing.share.util.SignUtil;
public class WX_Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public WX_Servlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");  
	        response.setCharacterEncoding("UTF-8"); 
        //΢�ż���ǩ��
		String signature=request.getParameter("signature");
		//ʱ���
		String timestamp=request.getParameter("timestamp");
		//�����
	   String nonce=request.getParameter("nonce");
	   //����ַ�
	   String echostr=request.getParameter("echostr");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			
			out.print(echostr);
		}
		out.close();
		out=null;
	}

	/*
	 * 
     * ����΢�ŷ�������������Ϣ 
     */  
	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
        // ���ú���ҵ���������Ϣ��������Ϣ  
        String respMessage = CoreService.processRequest(request);  
        // ��Ӧ��Ϣ  
        PrintWriter out = response.getWriter(); 
        
        out.print(respMessage);  
       
        out.close();  
	}
	
	
	
}
