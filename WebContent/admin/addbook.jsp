<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,bean.*,dao.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加图书</title>
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
		<%
			BookDAO bookDao = new BookDAO();
			List<Type> typeList = new ArrayList<>();
			typeList = bookDao.listType();
			request.setAttribute("typeList", typeList);
		%>
			<form action="${pageContext.request.contextPath}/admin/addbookservlet" method="post" enctype="multipart/form-data">
				<table width="978" border="0" cellspacing="10" cellpadding="0">
					<tbody>
						<tr>
							<td width="110" height="30" align="right">书名</td>
							<td width="364" height="30">
							<input type="text" name="bookname" required="required" class="addbook_text"></td>
							<td width="110" height="30" align="right">作者</td>
							<td width="369" height="30"><input type="text" name="author" required="required"
								class="addbook_text"></td>
						</tr>
						<tr>
							<td height="30" align="right">ISBN</td>
							<td height="30"><input type="text" name="isbn" required="required"
								class="addbook_text"></td>
							<td height="30" align="right">出版社</td>
							<td height="30"><input type="text" name="publisher" required="required"
								class="addbook_text"></td>
						</tr>
						<tr>
							<td height="30" align="right">价格</td>
							<td height="30">原价￥ <input type="text" name="oldprice" required="required"
								class="addbook_text_s" id="price1" onblur="valiprice1()"> &nbsp;&nbsp;&nbsp;现价￥<input required="required"
								type="text" name="newprice" class="addbook_text_s" id="price2" onblur="valiprice2()"></td>
							<td height="30" align="right">出版时间</td>
							<td height="30"><input type="date" name="pubtime" required="required"
								style="font-size: 16px"></td>
						</tr>
						<tr>
							<td height="30" align="right">开本</td>
							<td height="30"><select name="size" style="font-size: 16px">

									<option value="16开">16开</option>
									<option value="大16开">大16开</option>
									<option value="32开">32开</option>
									<option value="大32开">大32开</option>
									<option value="24开">24开</option>
									<option value="64开">64开</option>
							</select></td>
							<td height="30" align="right">纸张</td>
							<td height="30"><input type="text" name="paper"
								class="addbook_text"></td>
						</tr>
						<tr>
							<td height="30" align="right">包装</td>
							<td height="30" valign="middle"><input type="text"
								name="bookpackage" class="addbook_text"></td>
							<td height="30" align="right">是否套装</td>
							<td height="30"><p>
									<label> <input type="radio" name="suit" value="是">
										是
									</label> <label> <input type="radio" name="suit" value="否"
										checked="checked"> 否
									</label> <br>
								</p></td>
						</tr>
						<tr>
							<td height="30" align="right">所属分类</td>
							<td height="30"><select name="type" style="font-size: 16px">

							<c:forEach var="type" items="${typeList}">
								<option value="${type.typeId}">${type.typeName}</option>
								</c:forEach>

							</select></td>
							<td height="30" align="right">数量</td>
							<td height="30"><input type="text" name="mount" required="required"
								class="addbook_text" id="mount" onblur="valimount()"></td>
						</tr>
						<tr>
							<td height="30" align="right">图片</td>
							<td height="30"><input type="file" name="bookimg"></td>
							<td height="30" align="right">试读（PDF）</td>
							<td height="30"><input type="file" name="bookreread"></td>
						</tr>
						<tr>
							<td height="98" align="right" valign="top">书籍详情<br>
			  <span style="font-size: 12px">(不要超过200字)</span></td>
							<td colspan="3" valign="top"><textarea name="detail"
									cols="100" rows="6"></textarea></td>
						</tr>
						<tr>
							<td height="47" align="right" valign="top">&nbsp;</td>
							<td colspan="3" valign="top"><input type="submit"
								class="addbook_submit" value="添加"></td>
						</tr>
					</tbody>
				</table>

			</form>
		</div>
	</div>
	<script>
var reg = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
		
	function valiprice1(){
		var pn = document.getElementById("price1").value;

		if(!reg.test(pn)){
			alert("价格格式不正确，请重新输入");
		}
	}
	function valiprice2(){
		var pn = document.getElementById("price2").value;

		if(!reg.test(pn)){
			alert("价格格式不正确，请重新输入");
		}
	}
	function valimount(){
		var mount = document.getElementById("mount").value;
		
		if(!mou.test(mount)){
			alert("数量只能是正整数");
		}
	}
	</script>
</body>
</html>