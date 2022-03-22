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

	<div class="head_top_div">
		<div class="head_top_text">
			<%User user = (User)session.getAttribute("user");
				if (user!=null) {
			%>
			<a href="${pageContext.request.contextPath}/index/userinfo.jsp"><%=user.getName() %></a>&nbsp;&nbsp;
			<span>积分：</span>
			<%=user.getIntegral() %>&nbsp;&nbsp;
			<%
				if (user.getIntegral()>=2000){
			%>		
			<span style="color: #ff0000">VIP</span>
			<%
				}
			%>
			<a href="${pageContext.request.contextPath}/index/displayorderservlet">我的订单</a>
			&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/index/exitservlet">
			[退出]</a>
			<%
				} else {
			%>
			<a href="${pageContext.request.contextPath}/index/login.jsp">请登录</a>
			<%
				}
			%>
		</div>
	</div>
	<div class="head_bottom_div">
		<div class="head_bottom_logo">
			<a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/images/titlelogo.jpg"></a>
		</div>
		<div class="head_bottom_search">
			<div class="head_bottom_searchbo">
				<form action="${pageContext.request.contextPath}/index/searchservlet">
					<input class="head_bottom_search_input" type="text" name="searchname"> <input
						class="head_bottom_search_submit" type="submit" value="">
				</form>
			</div>
		</div>
		<a href="${pageContext.request.contextPath}/index/displaycartservlet">
			<div class="head_bottom_cart">
				<span>购物车</span>
			</div>
		</a>
	</div>
</body>
</html>