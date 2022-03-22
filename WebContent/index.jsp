<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,bean.*,dao.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZC书店</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
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

	<jsp:include page="index/top.jsp"></jsp:include>

	<div class="headcenter_border"></div>
	<div class="center_div">
		<div class="center_left">
			<div class="center_left_title">选择类别</div>
			<div class="dropdown">
				<button class="dropbtn">教育</button>
				<div class="dropdown-content">
					<a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=27">工具书</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=28">英语四六级</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=29">考研</a> <a
						href="${pageContext.request.contextPath}/index/searchtypeservlet?type=29">教师资格证考试</a>
				</div>
			</div>
			<div class="dropdown">
				<button class="dropbtn">文艺</button>
				<div class="dropdown-content">
					<a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=23">小说</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=24">传记</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=25">动漫/幽默</a> <a
						href="${pageContext.request.contextPath}/index/searchtypeservlet?type=26">艺术</a>
				</div>
			</div>
			<div class="dropdown">
				<button class="dropbtn">科技</button>
				<div class="dropdown-content">
					<a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=1">计算机/网络</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=2">医学</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=3">工业技术</a> <a
						href="${pageContext.request.contextPath}/index/searchtypeservlet?type=4">建筑</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=5">自然科学</a>
				</div>
			</div>
			<div class="dropdown">
				<button class="dropbtn">经管</button>
				<div class="dropdown-content">
					<a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=6">经济</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=7">管理</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=8">投资理财</a> <a
						href="${pageContext.request.contextPath}/index/searchtypeservlet?type=9">股票</a>
				</div>
			</div>
			<div class="dropdown">
				<button class="dropbtn">生活</button>
				<div class="dropdown-content">
					<a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=10">育儿</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=11">运动</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=12">休闲</a> <a
						href="${pageContext.request.contextPath}/index/searchtypeservlet?type=13">旅游</a>
				</div>
			</div>
			<div class="dropdown">
				<button class="dropbtn">励志</button>
				<div class="dropdown-content">
					<a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=14">励志/成功</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=15">心灵修养</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=16">人生哲学</a> <a
						href="${pageContext.request.contextPath}/index/searchtypeservlet?type=17">口才/演讲/辩论</a>
				</div>
			</div>
			<div class="dropdown">
				<button class="dropbtn">人文社科</button>
				<div class="dropdown-content">
					<a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=18">历史</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=19">文化</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=20">社会科学</a> <a
						href="${pageContext.request.contextPath}/index/searchtypeservlet?type=21">心理学</a> <a href="${pageContext.request.contextPath}/index/searchtypeservlet?type=22">法律</a>
				</div>
			</div>
		</div>
		<div class="center_right">
			<div>
				<ul id="banner_img">
					<li><img
						src="${pageContext.request.contextPath}/images/2020052518550914923.jpg"
						alt=""></li>
					<li><img
						src="${pageContext.request.contextPath}/images/2020052716100272966.jpg"
						alt=""></li>
					<li><img
						src="${pageContext.request.contextPath}/images/202005271711141253.jpg"
						alt=""></li>
				</ul>
			</div>
			<div class="banner_number">
				<a class="on"></a> <a></a> <a></a>
			</div>
		</div>
		<div class="center_notice_div">
			<div class="center_notice_top">公告</div>
			<div class="center_notice_content">
				<table width="100%" border="0" cellspacing="0" cellpadding="5">
					<tbody>
						<%
							NoticeDAO noticeDao = new NoticeDAO();
							List<Notice> noticeList = noticeDao.sortNoticeByAddtime();
							request.setAttribute("noticeList", noticeList);
						%>
						<c:forEach var="notice" items="${noticeList}" begin="0" end="8">

							<tr>
								<td width="65%"><a href="${pageContext.request.contextPath}/index/displaydetailservlet?id=${notice.noticeId}" target="_blank">${notice.name}</a></td>
								<td align="right">${notice.addTime}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</div>


	<div class="center_bottom_div">
		<%
			BookDAO bookDao = new BookDAO();
		%>
		<p class="center_bottom_title">新书上架</p>
		<div class="center_bottom_underline"></div>
		<div class="center_bottom_newbook">
			<%
				List<Book> addList = bookDao.sortBookByAddtime();
				request.setAttribute("addList", addList);
			%>
			<c:forEach var="book" items="${addList}" begin="0" end="4">
				<div class="center_bottom_first">

					<div class="center_bottom_first_img">
						<a href="${pageContext.request.contextPath}/index/displaybookservlet?id=${book.bookId}" target="_blank"><img src="${pageContext.request.contextPath}/${book.imageSrc}"
							height="180"></a>
					</div>
					<p>
						<a
							href="${pageContext.request.contextPath}/index/displaybookservlet?id=${book.bookId}"
							target="_blank">${book.name}</a>
					</p>
					<p>
						<span style="color: #ff0000"><fmt:formatNumber type="currency">${book.newPrice}</fmt:formatNumber></span>
					</p>
					<p style="color: #848484">上架时间：${book.addTime}</p>
				</div>
			</c:forEach>
		</div>
		<p class="center_bottom_title">畅销图书</p>
		<div class="center_bottom_underline"></div>
		<div class="center_bottom_newbook">
			<%
				List<Book> saleList = bookDao.sortBookBySale();
				request.setAttribute("saleList", saleList);
			%>
			<c:forEach var="book" items="${saleList}" begin="0" end="4">
				<div class="center_bottom_first">

					<div class="center_bottom_first_img">
						<a href="${pageContext.request.contextPath}/index/displaybookservlet?id=${book.bookId}" target="_blank"><img src="${pageContext.request.contextPath}/${book.imageSrc}"
							height="180"></a>
					</div>
					<p>
						<a
							href="${pageContext.request.contextPath}/index/displaybookservlet?id=${book.bookId}"
							target="_blank">${book.name}</a>
					</p>
					<p>
						<span style="color: #ff0000"><fmt:formatNumber type="currency">${book.newPrice}</fmt:formatNumber></span>
					</p>
					<p style="color: #848484">销量：${book.sale}本</p>
				</div>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="index/bottom.jsp"></jsp:include>
	<script>
		window.onload = function() {
			var img = document.getElementById("banner_img");
			var number = document.getElementsByClassName("banner_number")[0];
			var a = number.getElementsByTagName("a");
			for (i = 0; i < a.length; i++) {
				a[i].index = i;
				a[i].onmouseover = function() {
					for (j = 0; j < a.length; j++) {
						a[j].className = "";
					}
					a[this.index].className = "on";
					img.style.left = -this.index * 796 + "px";
				}
			}
		}
	</script>
</body>
</html>