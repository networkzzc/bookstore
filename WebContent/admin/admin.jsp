<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/adminstyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>

<%
	String message = (String)request.getAttribute("message");
	if(message!=null && !"".equals(message)){
%>
	<script type="text/javascript">
		alert("<%=message %>")
	</script>
<%}%>
	<div class="admin_center_div">
		<div class="admin_center_left">
			<div class="admin_left_div">
				<ul id="test">
					<li><a href="${pageContext.request.contextPath}/admin/adminbooklist">图书管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminuserlistservlet">前台用户</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminlistservlet">后台用户</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminorderlistservlet">订单管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminnoticelistservlet">公告管理</a></li>
				</ul>
			</div>
		</div>
		<div class="admin_center_right">欢迎使用ZC书店后台管理</div>
	</div>

</body>
</html>