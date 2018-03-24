<%@page contentType="image/png"%>
<jsp:useBean id="image" scope="page" class="com.yimidida.ows.home.server.util.makeCertPic" />
<%
	String str = image.getCertPic(response.getOutputStream());
	session.setAttribute("homeCode", str);
	out.clear();
	out = pageContext.pushBody();
%>