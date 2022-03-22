<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,bean.*,dao.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认订单</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="intop.jsp"></jsp:include>
	<div class="cartinfo_center_div">
		<form action="${pageContext.request.contextPath}/index/alipay.trade.page.pay.jsp" method="post">
			<div class="ordercorrect_top">
				<table width="600px">
					<tbody>
					<%
					User user = (User)session.getAttribute("user");
					%>
						<tr>
							<td width="17%" height="50" style="text-align: right">收货人</td>
							<td width="83%" height="50"><input type="text"
								class="register_input" name="receiver" required="required" value="<%=user.getName() %>">
							</td>
						</tr>
						<tr>
							<td height="30" style="text-align: right">&nbsp;</td>
							<td height="30">&nbsp;</td>
						</tr>
						<tr>
							<td height="50" style="text-align: right">手机号</td>
							<td height="50"><input type="text" class="register_input"
								name="phone" required="required" id="phone" onblur="valiphone()" value="<%=user.getPhone() %>"></td>
						</tr>
						<tr>
							<td height="30" style="text-align: right">&nbsp;</td>
							<td height="30"><span id="phonemsg"
								style="margin-left: 10px"></span></td>
						</tr>
						<tr>
							<td height="50" style="text-align: right">详细地址</td>
							<td height="50"><input type="text" class="register_input"
								name="address" required="required"></td>
						</tr>

					</tbody>
				</table>
			</div>
			<div>
				<span style="padding-left: 10px;">确认订单信息</span>
				<div class="cartinfo_center_underline"></div>



				<div class="ordercorrect_item_title">
					<div class="ordercorrect_item_title_name">
						<sapn>商品信息</sapn>
					</div>
					<div class="ordercorrect_item_title_oneprice">
						<span>单价</span>
					</div>
					<div class="ordercorrect_item_title_oneprice">
						<span>数量</span>
					</div>
					<div class="ordercorrect_item_title_oneprice">
						<span>优惠方式</span>
					</div>
					<div class="ordercorrect_item_title_sum">
						<span>小计</span>
					</div>
				</div>

				<!-- Begin -->
				
				<c:forEach var="reorder" items="${reOrderList}">
				<input name="bookid" value="${reorder.bookId}" style="display:none">
				<div class="ordercorrect_item">
					<div class="ordercorrect_item_img">
						<img src="${pageContext.request.contextPath}/${reorder.bookImg}" height="80px">
					</div>
					<div class="ordercorrect_item_name">
						<span>${reorder.bookName}</span>
					</div>
					<div class="ordercorrect_item_oneprice">
						<span><fmt:formatNumber type="currency">${reorder.bookPrice}</fmt:formatNumber></span>
						<input name="singleprice" value="${reorder.bookPrice}" style="display:none">
					</div>
					<div class="ordercorrect_item_oneprice">
						<span>${reorder.bookMount}</span>
						<input name="bookmount" value="${reorder.bookMount}" style="display:none">
					</div>
					<div class="ordercorrect_item_oneprice">
						
							<c:if test="${reorder.discount==1}">
								<span>无折扣</span>
							</c:if>
							<c:if test="${reorder.discount<1}">
								<span style="color: #ff0000;"><fmt:formatNumber type="number" pattern="">${reorder.discount * 10}</fmt:formatNumber>折</span>
							</c:if>
						
					</div>
					<input name="price" style="display:none" value="${reorder.bookPrice * reorder.bookMount * reorder.discount}">
					<div class="ordercorrect_item_sum"><fmt:formatNumber type="currency">${reorder.bookPrice * reorder.bookMount * reorder.discount}</fmt:formatNumber></div>
				</div>
				</c:forEach>
				<!-- End -->

			</div>
			<div class="ordercorrect_correct">
				<p>
					实付款：<span style="font-size: 24px; color: #ff0000;"><fmt:formatNumber type="currency"><%=request.getAttribute("sumPrice") %></fmt:formatNumber></span>
				</p>
				<p>
					<input name="sumprice" value="<%=request.getAttribute("sumPrice") %>" style="display:none">
					<a href="javascript:history.go(-1)" class="displaybook_first_button_buy">返回</a>
					<input type="submit" id="submit" value="确认订单" class="ordercorrect_submit">
				</p>
			</div>
		</form>
	</div>
    <jsp:include page="bottom.jsp"></jsp:include>
	<script>
		function valiphone() {
			var pn = document.getElementById("phone").value;
			var pnv = /^[1][3,4,5,7,8,9][0-9]{9}$/;
			if (!pnv.test(pn)) {
				document.getElementById("phonemsg").innerHTML = "<font color='#ff0000'>不是一个有效的手机号</font>";
				document.getElementById("submit").disabled=true;
			} else {
				document.getElementById("phonemsg").innerHTML = "<font color='#00ff00'>√</font>";
				document.getElementById("submit").disabled=false;
			}
		}
	</script>

</body>
</html>