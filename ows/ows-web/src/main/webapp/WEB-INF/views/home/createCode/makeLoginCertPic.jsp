<%@page contentType="image/png"%>
<jsp:useBean id="image" scope="page" class="com.yimidida.common.util.makeCertPic" />
<%
	String str = image.getCertPic(response.getOutputStream());
	session.setAttribute("certLoginCode", str);
	out.clear();
	out = pageContext.pushBody();
%>