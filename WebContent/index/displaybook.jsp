<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="myfun" uri="http://www.zcbookstore.com/function" %>
<%@ page import="java.util.*,bean.*,dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${book.name}"/>-ZC书店</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
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
<div class="displaybook_center">
<!--		上-->
		<div class="displaybook_first">
			<div class="displaybook_first_bigimg">		
				<img src="${pageContext.request.contextPath}/${book.imageSrc}">
			</div>
			<div class="displaybook_first_right">
				<p class="displaybook_first_title"><c:out value="${book.name}"/></p>
				<p class="displaybook_first_p">作者：<c:out value="${book.author}"/> 著</p>
				<p class="displaybook_first_p">出版社：<c:out value="${book.publisher}"/></p>
				<p class="displaybook_first_p">出版时间：<fmt:formatDate value="${book.pubTime}" pattern="yyyy年MM月"/></p>
				<p class="displaybook_first_newprice"><span style="font-size: 24px;"><fmt:formatNumber type="currency">${book.newPrice}</fmt:formatNumber></span></p>
				<p class="displaybook_first_p">定价<span style="text-decoration: line-through;"><fmt:formatNumber type="currency">${book.oldPrice}</fmt:formatNumber></span></p>
				<form action="" method="post" name="form1">
				<p>
					<input style="display:none" name="bookid" value="${book.bookId}">
					<div class="displaybook_first_mount">
						<input type="button" value="-" class="displaybook_first_mountbutton" onclick="reduction()">
						<input type="text" value="1" class="displaybook_first_mountinput" id="inputmount" onblur="check()" name="mount">
						<input type="button" value="+" class="displaybook_first_mountbutton" onclick="add()">
						<p class="displaybook_first_nowmount" id="recmount">(库存:${book.mount}件)</p>
					</div>
					 <div class="displaybook_first_button"><a href="#" class="displaybook_first_button_addcart" onclick="addcart()">加入购物车</a></div>
					<div class="displaybook_first_button" >
						<a href="#" class="displaybook_first_button_buy" onclick="buy()">立即购买</a>
					</div>
				</p>
				
				</form>
				</div>
		</div>
<!--	中-->
	<div>
	  <span class="displaybook_center_title">书籍详情</span>
		<div class="displaybook_center_underline"></div>
		<div class="displaybook_center_bookinfo">
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tbody>
    <tr>
      <td width="32%">开本：<c:out value="${book.size}"/></td>
      <td width="42%">纸张：<c:out value="${book.paper}"/></td>
      <td width="26%">包装：<c:out value="${book.bookPackage}"/></td>
    </tr>
    <tr>
      <td>是否套装：<c:out value="${book.suit}"/></td>
      <td colspan="2">国际标准书号ISBN：<c:out value="${book.ISBN}"/></td>
      </tr>
    <tr>
      <td colspan="3">所属分类：<c:out value="${bookType}"/></td>
      </tr>
  </tbody>
</table>

	  </div>
		
		<span class="displaybook_center_title">内容简介</span>
		<div class="displaybook_center_underline"></div>
		<p><c:out value="${book.detail}"/>
		</p>
		<p>&nbsp;</p>
		<c:if test="${not empty book.rereadSrc}">
		<span class="displaybook_center_title">在线试读</span>
		<div class="displaybook_center_underline"></div>
		<div id="pdf" style="margin-bottom: 50px"></div>
		</c:if>
		<span class="displaybook_center_title">评论</span>
		<div class="displaybook_center_underline"></div>
<!--		评论内容-->
	<div>
		<c:forEach var="review" items="${reviewList}">
		<div class="displaybook_center_reviewdiv">
				<div class="displaybook_center_reviewleft">
					<p><c:forEach begin="1" end="${review.grade}">
					<img src="${pageContext.request.contextPath}/images/icon_star_active.png" height="22px"></c:forEach>
					<c:forEach begin="1" end="${5-review.grade}"><img src="${pageContext.request.contextPath}/images/icon_star_default.png" height="22px">
					</c:forEach>
					</p>
					<p>${review.detail}</p>
					<p>${review.addTime}</p>
				</div>
				<div class="displaybook_center_reviewright">
				<c:if test="${review.userId>0}">
					<c:out value="${myfun:find(review.userId)}"/>
				</c:if>
				<c:if test="${review.userId<=0}">
					匿名用户
				</c:if>
				</div>		
		</div>
		</c:forEach>
	</div>
	</div>
	</div>
	    <jsp:include page="bottom.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/js/PDFObject.js" type="text/javascript"></script>
<script type="text/javascript">
	var input = document.getElementById("inputmount");
	function add(){
		input.value = parseInt(input.value)+1;
	}
	
	function reduction(){
		if(input.value <= 1){
			input.value = 1;
		}else{
			input.value = parseInt(input.value)-1;
		}
	}
	
	function check(){
		var reg = /^\+?[1-9][0-9]*$/;
		
		if(!reg.test(input)){
			input.value = parseInt(input.value);
		}
		if(parseInt(input.value) >= parseInt(${book.mount})){
		
			input.value = parseInt(${book.mount});
		}
	}
	
	var options = {
		height:"550px",
		pdfOpenParams:{view:'FitV',page:'0'},
		name:"mans",
		fallbackLink:"<p>您的浏览器不支持</p>"
	};
	PDFObject.embed("${pageContext.request.contextPath}/${book.rereadSrc}","#pdf",options);
	
	function addcart(){
		document.form1.action="addcartservlet";
		document.form1.submit();
	}
	
	function buy(){
		document.form1.action="nowcorrectorderservlet";
		document.form1.submit();
	}
</script>
</body>
</html>