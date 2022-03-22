<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加公告</title>
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
		<form action="${pageContext.request.contextPath}/admin/addnoticeservlet" method="post">
			      <table width="866" border="0" cellspacing="10" cellpadding="0">
        <tbody>
          <tr>
            <td width="126" height="42" align="right">标题</td>
            <td width="710" height="42">
            <input type="text" name="title" class="addbook_text" required="required"></td>
          </tr>
          <tr>
            <td height="188" align="right" valign="top">内容<br>
			  <span style="font-size: 12px">(不要超过200字)</span></td>
            <td height="188" valign="top"><textarea name="detail" cols="100" rows="6" onKeyDown="if (this.value.length>=200){event.returnValue=false}"></textarea></td>
          </tr>
          <tr>
            <td height="47" align="right" valign="top">&nbsp;</td>
            <td valign="top"><input type="submit" class="addbook_submit" id="submit" value="添加" ></td>
          </tr>
        </tbody>
      </table>
      </form>
		</div>
	</div>
</body>
</html>