<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
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
<%}%>
<jsp:include page="intop.jsp"></jsp:include>
<div class="login_center_div">
  <div class="register_center_div"> <span style="font-size: 20px; font-family: '黑体';">新用户注册 </span><br>
    <div class="register_center">
		<form action="registerservlet" method="post">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tbody>
            <tr>
              <td width="17%" height="50" style="text-align: right">用户名</td>
              <td width="83%" height="50"><input type="text" class="register_input" name="username" required="required"></td>
            </tr>
            <tr>
              <td height="30" style="text-align: right">&nbsp;</td>
              <td height="30">&nbsp;</td>
            </tr>
            <tr>
              <td height="50" style="text-align: right">密码</td>
              <td height="50"><input type="password" class="register_input" name="password" id="pw1" required="required"></td>
            </tr>
            <tr>
              <td height="30" style="text-align: right">&nbsp;</td>
              <td height="30"></td>
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
              <td height="50" style="text-align: right">手机号</td>
              <td height="50"><input type="text" class="register_input" name="phone" required="required" id="phone" onkeyup="valiphone()"></td>
            </tr>
            <tr>
              <td height="30" style="text-align: right">&nbsp;</td>
              <td height="30"><span id="phonemsg" style="margin-left: 10px"></span></td>
            </tr>
            <tr>
              <td height="50" style="text-align: right">E-mail</td>
              <td height="50"><input type="text" class="register_input" name="email" id="email" required="required" onkeyup="valimail()"></td>
            </tr>
            <tr>
              <td height="30" style="text-align: right">&nbsp;</td>
              <td height="30"><span id="mailmsg" style="margin-left: 10px"></span></td>
            </tr>
            <tr>
              <td height="50" style="text-align: right">性别</td>
              <td height="50"><p style="margin-left: 10px">
                  <label>
                    <input type="radio" name="sex" value="男" id="RadioGroupSex_0" checked="checked">
                    男</label>
                  <label>
                    <input type="radio" name="sex" value="女" id="RadioGroupSex_1">
                    女</label>
                  <br>
                </p></td>
            </tr>
            <tr>
              <td height="50" style="text-align: right">出生日期</td>
              <td height="50"><input type="date" name="birth" id="date" class="register_input" required="required"></td>
            </tr>
            <tr>
              <td height="50" colspan="2" style="text-align: center"><input id="submit" type="submit" 
					  disabled="disabled" value="立即注册" class="login_center_right_submit" onClick="vali()"></td>
            </tr>
          </tbody>
        </table>
	  </form>
    </div>
  </div>
</div>
    <jsp:include page="bottom.jsp"></jsp:include>
<script>
	var ismail = false;
	var ispw = false;
	var isph = false;
	function validatepw(){
		var pw1 = document.getElementById("pw1").value;
		var pw2 = document.getElementById("pw2").value;
		if(pw1==pw2){
			document.getElementById("pwmsg").innerHTML="<font color='#00ff00'>√</font>";
			ispw = true;
		}else{
			document.getElementById("pwmsg").innerHTML="<font color='#ff0000'>两次输入的密码不一致</font>";
			ispw = false;
		}
		vali();
	}

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
		if(ismail && ispw && isph){
			document.getElementById("submit").disabled=false;
		}else{
			document.getElementById("submit").disabled=true;
		}
	}
</script>
</body>
</html>