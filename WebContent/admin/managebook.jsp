<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,bean.*,dao.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理图书</title>
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
					<li><a class="active" href="${pageContext.request.contextPath}/admin/adminbooklist">图书管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminuserlistservlet">前台用户</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminlistservlet">后台用户</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminorderlistservlet">订单管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminnoticelistservlet">公告管理</a></li>
				</ul>
			</div>
		</div>
	<div class="admin_center_right">
		<div class="admin_center_addbookdiv">
			<a href="${pageContext.request.contextPath}/admin/addbook.jsp" class="admin_center_addbook">添加</a>
	  </div>
		<div class="booklist">
		  <table width="100%" border="1" cellspacing="0" cellpadding="5" class="booklist_table">
		    <tbody>
		      <tr>
		      	<td width="5%"><strong>编号</strong></td>
		        <td width="18%"><strong>书名</strong></td>
		        <td width="10%"><strong>作者</strong></td>
		        <td width="10%"><strong>ISBN</strong></td>
		        <td width="5%"><strong>销量</strong></td>
		        <td width="7%"><strong>库存</strong></td>
		        <td width="8%"><strong>原价</strong></td>
		        <td width="8%"><strong>现价</strong></td>
		        <td width="10%"><strong>出版时间</strong></td>
		        <td width="10%"><strong>上架时间</strong></td>
		        <td width="9%"><strong>操作</strong></td>
	          </tr>
	          <c:forEach var="book" items="${bookList}">
		      <tr>
		      	<td height="30">${book.bookId}</td>
		        <td height="30">${book.name}</td>
		        <td height="30">${book.author}</td>
		        <td height="30">${book.ISBN}</td>
		        <td height="30">${book.sale}</td>
		        <td height="30">${book.mount}</td>
		        <td height="30"><fmt:formatNumber type="currency">${book.oldPrice}</fmt:formatNumber></td>
		        <td height="30"><fmt:formatNumber type="currency">${book.newPrice}</fmt:formatNumber></td>
		        <td height="30">${book.pubTime}</td>
		        <td height="30">${book.addTime}</td>
				  <td height="30"><a href="${pageContext.request.contextPath}/admin/adminupdatebookservlet?id=${book.bookId}">修改</a>&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/admindeletebookservlet?id=${book.bookId}" onclick="return isdel();">删除</a></td>
	          </tr>
	          </c:forEach>
	        </tbody>
	      </table>
			
	  </div>
	  <div class="booklist_bottom">
		<div class="booklist_bottom_left">
				显示第${start}至第${end}项结果，共${recordCount}项
			</div>
		  <div class="booklist_bottom_right">
			<ul class="booklist_bottom_ul">
				<c:choose>
				<c:when test="${pageIndex==1}">
					<li>首页</li>
					<li>上页</li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/admin/adminbooklist?pageIndex=1">首页</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminbooklist?pageIndex=${pageIndex-1}">上页</a></li>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${pageIndex==pageCount}">
					<li>下页</li>
					<li>末页</li>
				</c:when>
				<c:otherwise>
			    	<li><a href="${pageContext.request.contextPath}/admin/adminbooklist?pageIndex=${pageIndex+1}">下页</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminbooklist?pageIndex=${pageCount}">末页</a></li>
				</c:otherwise>
				</c:choose>
		    </ul>
				
		  </div>
		</div>
    </div>
	
	</div>
<script>
		function isdel(){
			if(confirm('确定删除？')){
				return true;
			}
			return false;
		}
	</script>
</body>
</html>