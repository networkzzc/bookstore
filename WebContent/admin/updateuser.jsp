<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
				<li><a class="active" href="${pageContext.request.contextPath}/admin/adminuserlistservlet">前台用户</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/adminuserlistservlet">后台用户</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/adminorderlistservlet">订单管理</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/adminnoticelistservlet">公告管理</a></li>
			</ul>
		</div>
	</div>
	<div class="admin_center_right">
	<form action="${pageContext.request.contextPath}/admin/adminupdateusercorrectservlet" method="post">
		<table width="504" border="0" cellspacing="10" cellpadding="0">
        <tbody>
          <tr>
          <input name="userid" value="${user.userId}" style="display:none">
            <td width="110" height="30" align="right">用户名</td>
            <td width="364" height="30">
            <input type="text" name="username" class="addbook_text" required="required" value="${user.name}"></td>
          </tr>
          <tr>
            <td height="30" align="right">手机号</td>
            <td height="30"><input type="text" name="phone" id="phone" class="addbook_text" onkeyup="valiphone()" value="${user.phone}"></td>
          </tr>
          <tr>
            <td height="18" align="right">&nbsp;</td>
            <td height="18"><span id="phonemsg"></span></td>
          </tr>
          <tr>
            <td height="30" align="right">Email</td>
            <td height="30"><input type="text" name="email" id="email" class="addbook_text" onkeyup="valimail()" value="${user.email}"></td>
          </tr>
          <tr>
            <td height="19" align="right">&nbsp;</td>
            <td height="19"><span id="mailmsg"></span></td>
          </tr>
          <tr>
            <td height="30" align="right">性别</td>
            <td height="30">
				<label>
                <input type="radio" name="sex" value="男" <c:if test="${user.sex eq '男'}">checked="checked"</c:if>>
                男</label>
              	<label>
                <input type="radio" name="sex" value="女" <c:if test="${user.sex eq '女'}">checked="checked"</c:if>>
                女</label></td>
          </tr>
          <tr>
            <td height="30" align="right">出生日期</td>
            <td height="30" valign="middle"><input type="date" name="birth" style="font-size: 16px" value="${user.birth}"></td>
          </tr>
          <tr>
            <td height="30" align="right">积分</td>
            <td height="30"><input type="text" name="integral" class="addbook_text" value="${user.integral}"></td>
          </tr>
          <tr>
            <td height="47" align="right" valign="top">&nbsp;</td>
            <td valign="top"><input type="submit" class="addbook_submit" id="submit" value="修改"></td>
          </tr>
        </tbody>
      	</table>
	</form>
	</div>

</div>
<script>
	var ismail = false;
	var isph = false;

	function valimail(){
		var x = document.getElementById("email").value;
		var atpos = x.indexOf("@");
		var dotpos = x.lastIndexOf(".");
		if(atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length){
			document.getElementById("mailmsg").innerHTML="<font color='#ff0000'>不是一个有效的邮件地址</font>";
			ismail = false;
		}else{
			document.getElementById("mailmsg").innerHTML="<font color='#00ff00'>√</font>";
			ismail = true;
		}
		vali();
	}
	
	function valiphone(){
		var pn = document.getElementById("phone").value;
		var pnv = /^[1][3,4,5,7,8,9][0-9]{9}$/;
		if(!pnv.test(pn)){
			document.getElementById("phonemsg").innerHTML="<font color='#ff0000'>不是一个有效的手机号</font>";
			isph = false;
		}else{
			document.getElementById("phonemsg").innerHTML="<font color='#00ff00'>√</font>";
			isph = true;
		}
		vali();
	}
	
	function vali(){
		if(ismail && isph){
			document.getElementById("submit").disabled=false;
		}else{
			document.getElementById("submit").disabled=true;
		}
	}
	</script>
</body>
</html>