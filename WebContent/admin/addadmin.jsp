<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,bean.*,dao.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加管理员</title>
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
					<li><a
						class="active" href="${pageContext.request.contextPath}/admin/adminbooklist">图书管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminuserlistservlet">前台用户</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminlistservlet">后台用户</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminorderlistservlet">订单管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/adminnoticelistservlet">公告管理</a></li>
				</ul>
			</div>
		</div>
		<div class="admin_center_right">
		<form action="${pageContext.request.contextPath}/admin/addadminservlet" method="post">
		<table width="528" border="0" cellspacing="10" cellpadding="0">
        <tbody>
          <tr>
            <td width="143" height="30" align="right">用户名</td>
            <td width="331" height="30">
            <input type="text" name="adminname" class="addbook_text" required="required"></td>
          </tr>
          <tr>
            <td height="30" align="right">密码</td>
            <td height="30"><input type="password" name="password" id="pw1" class="addbook_text"></td>
          </tr>
          <tr>
            <td height="30" align="right">确认密码</td>
            <td height="30"><input type="password" id="pw2" class="addbook_text" onkeyup="validatepw()"></td>
          </tr>
          <tr>
            <td height="19" align="right">&nbsp;</td>
            <td height="19"><span id="pwmsg"></span></td>
          </tr>
          <tr>
            <td height="47" align="right" valign="top">&nbsp;</td>
            <td valign="top"><input type="submit" class="addbook_submit" id="submit" value="添加" disabled="disabled" ></td>
          </tr>
        </tbody>
      </table>
		</form>
		</div>
</div>

	<script>

	function validatepw(){
		var pw1 = document.getElementById("pw1").value;
		var pw2 = document.getElementById("pw2").value;
		if(pw1==pw2){
			document.getElementById("pwmsg").innerHTML="<font color='#00ff00'>√</font>";
			document.getElementById("submit").disabled=false;
		}else{
			document.getElementById("pwmsg").innerHTML="<font color='#ff0000'>两次输入的密码不一致</font>";
			document.getElementById("submit").disabled=true;
		}
	}
	</script>
</body>
</html>