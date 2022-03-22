<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,bean.*,dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<style>
.star_score { 
	float: left;
	background:url(../images/stark2.png); 
	width:160px; 
	height:21px;  
	position:relative; 
}
.star_score a{ 
	height:21px; 
	display:block; 
	text-indent:-999em; 
	position:absolute;
	left:0;
}
#starttwo .star_score { 
	background:url(../images/starky.png);
}
#starttwo .star_score a:hover{ 
	background:url(../images/starsy.png);
	left:0;
}
#starttwo .star_score a.clibg{ 
	background:url(../images/starsy.png);
	left:0;
}
.fenshu{
    border:none;
    width: 10px;
}
    </style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<%
User user = (User)session.getAttribute("user");
int bookId = Integer.parseInt(request.getParameter("id"));
Book book = new Book();
BookDAO bookDao = new BookDAO();
book = bookDao.selectById(bookId);
%>
<div class="addview_div">
        <form action="${pageContext.request.contextPath}/index/subreviewservlet" method="post">
        <span>评价</span>
        <div class="center_bottom_underline"></div>
        <div class="addview_top">
            <div class="addview_top_img">
                <img src="${pageContext.request.contextPath}/<%=book.getImageSrc()%>" height="70">
            </div>
            <div class="addview_top_name">
            <input name="bookid" value="<%=bookId%>" style="display:none">
                <a href="${pageContext.request.contextPath}/index/displaybookservlet?id=<%=bookId%>" target="_blank"><%=book.getName()%></a>
            </div>
            <div class="addview_top_author">
                <span style="padding-left: 10px;"><%=book.getAuthor()%> 著</span>
            </div>
        </div>
        <div class="addview_center_first">
            <div id="starttwo">
                <div class="star_score"></div>
               <p>您的评分：<input type="text" name="grade" class="fenshu" value="" readonly="readonly">分</p>
            </div>
        </div>
        <div class="addview_center_text">
            <span>写评论（字数不得超过200）</span><input type="checkbox" name="annoymous" value="1">匿名
            <textarea name="detail" rows="5" cols="100" onKeyDown="if (this.value.length>=200){event.returnValue=false}">
            </textarea>
        </div>
        <div class="addview_center_button">
            <input type="submit" value="提交" class="cartinfo_center_top_submit" style="background-color: #ff5000;">
        </div>
    </form>
    </div>
    <jsp:include page="bottom.jsp"></jsp:include>
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/startScore.js"></script>
    <script>
        scoreFun($("#starttwo"),{
              fen_d:22,//每一个a的宽度
              ScoreGrade:5//a的个数 10或者
            })
       </script>
</body>
</html>