<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="admin_left_div">
        <ul id="test">
            <li><a href="#">图书管理</a></li>
            <li><a href="#">前台用户</a></li>
            <li><a href="#">后台用户</a></li>
            <li><a href="#">订单管理</a></li>
        </ul>
    </div>

<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script>
    $("#test li").click(function(){
        $(this).siblings('li').removeClass('active');
        $(this).addClass('active');
    })
</script>
</body>
</html>