<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=request.getParameter("searchname") %>-ZC书店</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
	<p class="search_null_result">
		非常抱歉，没有找到与<%=request.getParameter("searchname") %>相关的书籍
	</p>
	    <jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>