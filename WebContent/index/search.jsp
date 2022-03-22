<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,bean.*,dao.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
		<%=request.getAttribute("searchname") %>
-ZC书店</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="search_center">
<div>
    <ul class="search_list">
	<c:forEach var="book" items="${bookList}">
      <li><div class="search_list_img"><a href="${pageContext.request.contextPath}/index/displaybookservlet?id=${book.bookId}" target="_blank"><img src="${pageContext.request.contextPath}/${book.imageSrc}" height="200"></a></div>
		  <p>&nbsp;</p>
        <p class="search_list_title"><a href="${pageContext.request.contextPath}/index/displaybookservlet?id=${book.bookId}" target="_blank">${book.name}</a></p>
        <p class="search_list_now_price"><fmt:formatNumber type="currency">${book.newPrice}</fmt:formatNumber><span>定价：<fmt:formatNumber type="currency">${book.oldPrice}</fmt:formatNumber></span></p>
        <p class="search_list_other">${book.author}&nbsp;著/${book.pubTime}/${book.publisher}</p>
      </li>
      </c:forEach>
    </ul>
  </div>
<ul>
		</ul>
		</div>
		    <jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>