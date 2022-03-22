<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
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
<%
	String message = (String)request.getAttribute("message");
	if(message!=null && !"".equals(message)){
%>
	<script type="text/javascript">
		alert("<%=message %>")
	</script>
<%}%>
<div class="login_center_div">
  <div class="login_center_left"> <img src="${pageContext.request.contextPath}/images/login.jpg" width="600"> </div>
  <div class="login_center_right">
    <div class="login_center_right_in"> <span style="font-size: 20px; font-family: '黑体';">管理员登录 </span><br>
      <form action="${pageContext.request.contextPath}/admin/adminloginservlet" method="post">
          <input type="text" name="adminname" class="login_center_right_input" placeholder="用户名">
          <input type="password" name="password" class="login_center_right_input" placeholder="密码">
          <input type="submit" value="登录" class="login_center_right_submit">
      </form>
    </div>
  </div>
</div>
</body>
</html>