<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,bean.*,dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%
String message = (String)request.getAttribute("message");
if(message!=null && !"".equals(message)){
%>
<script type="text/javascript">
	alert("<%=message %>")
</script>
<%}
		User user = (User) session.getAttribute("user");
	%>
<jsp:include page="intop.jsp"></jsp:include>
<div class="login_center_div">
  <div class="register_center_div"> <span style="font-size: 20px; font-family: '黑体';">修改密码 </span><br>
    <div class="register_center">
      <form action="upuserpasswdservlet" method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tbody>
            <tr>
              <td width="17%" height="50" style="text-align: right">用户名</td>
              <td width="83%" height="50"><input type="text" class="register_input" name="username" required="required" id="username" value="<%=user.getName()%>" readonly="readonly"></td>
            </tr>
            <tr>
              <td height="30" style="text-align: right">&nbsp;</td>
              <td height="30">&nbsp;</td>
            </tr>
            <tr>
              <td height="50" style="text-align: right">旧密码</td>
              <td height="50"><input type="password" class="register_input" name="oldpassword" required="required"></td>
            </tr>
            <tr>
              <td height="50" style="text-align: right">新密码</td>
              <td height="50"><input type="password" class="register_input" name="password" id="pw1" required="required"></td>
			  </tr>
            <tr>
              <td height="50" style="text-align: right">确认密码</td>
              <td height="50"><input type="password" class="register_input" id="pw2" onblur="validatepw()" required="required"></td>
            </tr>
            <tr>
              <td height="30" style="text-align: right">&nbsp;</td>
              <td height="30"><span id="pwmsg" style="margin-left: 10px"></span></td>
            </tr>
            <tr>
              <td height="50" colspan="2" style="text-align: center"><input id="submit" type="submit" 
					  disabled="disabled" value="确认修改" class="login_center_right_submit" ></td>
            </tr>
          </tbody>
        </table>
      </form>
    </div>
  </div>
</div>
    <jsp:include page="bottom.jsp"></jsp:include>
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