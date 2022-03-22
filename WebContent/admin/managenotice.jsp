<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/adminstyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
	<%
		String message = (String) request.getAttribute("message");
		if (message != null && !"".equals(message)) {
	%>
	<script type="text/javascript">
		alert("<%=message%>")
	</script>
	<%
		}
	%>

	<div class="admin_center_div">
		<div class="admin_center_left">
			<div class="admin_left_div">
				<ul id="test">
					<li><a
						href="${pageContext.request.contextPath}/admin/adminbooklist">图书管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/admin/adminuserlistservlet">前台用户</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminlistservlet">后台用户</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminorderlistservlet">订单管理</a></li>
					<li><a class="active" href="${pageContext.request.contextPath}/admin/adminnoticelistservlet">公告管理</a></li>
				</ul>
			</div>
		</div>
		<div class="admin_center_right">
				<div class="admin_center_addbookdiv">
			<a href="${pageContext.request.contextPath}/admin/addnotice.jsp" class="admin_center_addbook">添加</a>
	  </div>
		<div class="booklist">
		  <table width="100%" border="1" cellspacing="0" cellpadding="5" class="booklist_table">
		    <tbody>
		      <tr>
			    <td width="19%"><strong>编号</strong></td>
		        <td width="52%"><strong>标题</strong></td>
		        <td width="18%"><strong>添加日期</strong></td>
		        <td width="11%"><strong>操作</strong></td>
	          </tr>
	          <c:forEach var="notice" items="${noticeList}">
		      <tr>
		        <td>${notice.noticeId}</td>
		        <td height="30">${notice.name}</td>
		        <td height="30">${notice.addTime}</td>
				  <td height="30"><a href="${pageContext.request.contextPath}/admin/admindeletenoticeservlet?id=${notice.noticeId}" onclick="return isdel();">删除</a></td>
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
					<li><a href="${pageContext.request.contextPath}/admin/adminnoticelistservlet?pageIndex=1">首页</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminnoticelistservlet?pageIndex=${pageIndex-1}">上页</a></li>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${pageIndex==pageCount}">
					<li>下页</li>
					<li>末页</li>
				</c:when>
				<c:otherwise>
			    	<li><a href="${pageContext.request.contextPath}/admin/adminnoticelistservlet?pageIndex=${pageIndex+1}">下页</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminnoticelistservlet?pageIndex=${pageCount}">末页</a></li>
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