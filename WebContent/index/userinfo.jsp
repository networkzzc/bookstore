<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,bean.*,dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<style type="text/css">
a:link {
	color: #888888;
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #888888;
}
a:hover {
	text-decoration: underline;
	color: #888888;
}
a:active {
	text-decoration: none;
	color: #888888;
}
</style>
</head>
<body>
<%	String message = (String)request.getAttribute("message");
if(message!=null && !"".equals(message)){
%>
<script type="text/javascript">
	alert("<%=message %>")
</script>
<%}
	User user = (User)session.getAttribute("user");
%>
<jsp:include page="intop.jsp"></jsp:include>
		<div class="login_center_div">
			<div class="register_center_div">
				<span style="font-size: 20px; font-family: '黑体';">用户信息 </span>
				<table width="100%" border="0" cellspacing="0" cellpadding="10">
  <tbody>
    <tr>
      <td width="24%" align="right">用户名</td>
      <td width="76%"><%=user.getName() %></td>
    </tr>
    <tr>
      <td align="right">性别</td>
      <td><%=user.getSex() %></td>
    </tr>
    <tr>
      <td align="right">出生日期</td>
      <td><%=user.getBirth() %></td>
    </tr>
    <tr>
      <td align="right">积分</td>
      <td><%=user.getIntegral() %></td>
    </tr>
    <tr>
      <td align="right">手机号</td>
      <td><%=user.getPhone() %></td>
    </tr>
    <tr>
      <td align="right">E-mail</td>
      <td><%=user.getEmail() %></td>
    </tr>
    <tr>
      <td align="right">&nbsp;</td>
      <td><a href="${pageContext.request.contextPath}/index/updateuser.jsp?user=<%=user %>">修改信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/index/changepasswd.jsp?user=<%=user %>">修改密码</a></td>
    </tr>
  </tbody>
</table>

		  </div>
		</div>
		    <jsp:include page="bottom.jsp"></jsp:include>
	
</body>
</html>