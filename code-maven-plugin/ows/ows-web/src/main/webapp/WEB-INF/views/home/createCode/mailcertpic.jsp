<%@page contentType="image/jpeg"%>
<jsp:useBean id="image" scope="page" class="com.yimidida.ows.home.server.util.makeCertPic" />
<%
	String str = image.getCertPic(response.getOutputStream());
	session.setAttribute("mailcode", str);
	out.clear();
	out = pageContext.pushBody();
%>