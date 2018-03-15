package org.hbhk.aili.weixing.server.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.aili.weixing.server.service.TalkService;
import org.hbhk.aili.weixing.share.util.SignUtil;

public class Talk_Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public Talk_Servlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
        // ���ú���ҵ���������Ϣ��������Ϣ  
        String respMessage = TalkService.processRequest(request);  
        // ��Ӧ��Ϣ  
        PrintWriter out = response.getWriter(); 
        
        out.print(respMessage);  
       
        out.close(); 
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
