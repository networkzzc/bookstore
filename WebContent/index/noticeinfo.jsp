<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="intop.jsp"></jsp:include>
	<div style="width:800px; margin:auto;">

	<h1 align="center">${notice.name}</h1>
  	<p style="font-size: 20px">&nbsp;&nbsp;&nbsp;&nbsp;${notice.detail} </p>
  </div>
      <jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>