<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,bean.*,dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="login_top_div">
    <div style="float: left;">
        <a href="${pageContext.request.contextPath}/admin/admin.jsp"><img src="${pageContext.request.contextPath}/images/titlelogo.jpg"
            height="80"></a>
    </div>
	<div class="admin_top_text">
	<%
	Admin admin = (Admin)session.getAttribute("admin");
	%>
        <p>管理员：<span><%=admin.getName() %></span>&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/admin/adminexitservlet">[退出]</a></p>
    </div>
        
</div>
</body>
</html>