<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,bean.*,dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改信息</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%
		User user = (User) session.getAttribute("user");
	%>
	<jsp:include page="intop.jsp"></jsp:include>
	<div class="login_center_div">
		<div class="register_center_div">
			<span style="font-size: 20px; font-family: '黑体';">修改信息 </span><br>
			<div class="register_center">
				<form action="updateuserservlet" method="post">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td width="17%" height="50" style="text-align: right">用户名</td>
								<td width="83%" height="50"><input type="text" class="register_input" name="username" required="required" id="username" value="<%=user.getName()%>" readonly="readonly"></td>
							</tr>
							<tr>
								<td height="42" style="text-align: right">手机号</td>
								<td height="42"><input type="text" class="register_input"
									name="phone" required="required" id="phone"
									onblur="valiphone()" value=<%=user.getPhone()%>></td>
							</tr>
							<tr>
								<td height="30" style="text-align: right">&nbsp;</td>
								<td height="30"><span id="phonemsg"
									style="margin-left: 10px"></span></td>
							</tr>
							<tr>
								<td height="50" style="text-align: right">邮箱</td>
								<td height="50"><input type="text" class="register_input"
									name="email" id="email" required="required"
									onkeyup="valimail()" value=<%=user.getEmail()%>></td>
							</tr>
							<tr>
								<td height="30" style="text-align: right">&nbsp;</td>
								<td height="30"><span id="mailmsg"
									style="margin-left: 10px"></span></td>
							</tr>
							<tr>
								<td height="50" style="text-align: right">性别</td>
								<td height="50"><p style="margin-left: 10px">
										<%
											String uSex = user.getSex();
											if (uSex.equals("男")) {
										%>
										<label> <input type="radio" name="sex" value="男"
											id="RadioGroupSex_0" checked="checked"> 男
										</label> <label> <input type="radio" name="sex" value="女"
											id="RadioGroupSex_1"> 女
										</label>
										<%
											} else {
										%>
										<label> <input type="radio" name="sex" value="男"
											id="RadioGroupSex_0"> 男
										</label> <label> <input type="radio" name="sex" value="女"
											id="RadioGroupSex_1" checked="checked"> 女
										</label>
										<%
											}
										%>

										<br>
									</p></td>
							</tr>
							<tr>
								<td height="50" style="text-align: right">出生日期</td>
								<td height="50"><input type="date" name="birth" id="date"
									class="register_input" value="<%=user.getBirth()%>"></td>
							</tr>
							<tr>
								<td height="50" colspan="2" style="text-align: center"><input
									id="submit" type="submit" value="修改"
									class="login_center_right_submit" onClick="vali()"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	    <jsp:include page="bottom.jsp"></jsp:include>
	<script>
	var ismail = true;
	var isph = true;
	
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