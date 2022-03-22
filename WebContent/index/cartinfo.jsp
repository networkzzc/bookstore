<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,bean.*,dao.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	User user = (User) session.getAttribute("user");
%>
<title><%=user.getName()%>的购物车-ZC书店</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
	<div class="cartinfo_center_div">
		<form method="post" action="${pageContext.request.contextPath}/index/correctorderservlet">
			<div class="cartinfo_center_top">
				<div class="cartinfo_center_top_left">
					<span>购物车</span>
				</div>
				<div class="cartinfo_center_top_right">
					合计：<span id="sumPrice">0.00</span>元 
					<input type="submit" value="结算"
						class="cartinfo_center_top_submit" id="submit">
				</div>
			</div>
			<div class="cartinfo_center_underline"></div>
			<c:if test="${empty cartList}">
				购物车空空如也
			</c:if>
			<c:if test="${not empty cartList}">
			<div class="cartinfo_center_book_title">
				<div class="cartinfo_center_book_title_checkall">
					<input type="checkbox" id="checkall"><span>全选</span>
				</div>
				<div class="cartinfo_center_book_title_name">
					<span>商品信息</span>
				</div>
				<div class="cartinfo_center_book_title_price">
					<span>单价</span>
				</div>
				<div class="cartinfo_center_book_title_mount">
					<span>数量</span>
				</div>
				<div class="cartinfo_center_book_title_price">
					<span>金额</span>
				</div>
				<div class="cartinfo_center_book_title_ope">
					<span>操作</span>
				</div>
			</div>

			<!-- Begin -->
			<c:forEach var="cart" items="${cartList}">
			
			<div class="cartinfo_center_book">
				<div class="cartinfo_center_check">
					<input type="checkbox" class="cartinfo_center_checkbox" name="cartid" value="${cart.cartId}">
				</div>
				<div class="cartinfo_center_img">
					<a href="${pageContext.request.contextPath}/index/displaybookservlet?id=${cart.bookId}">
					<img src="${pageContext.request.contextPath}/${cart.bookImgSrc}" height="125px"></a>
				</div>
				<div class="cartinfo_center_name">
					<span><a href="${pageContext.request.contextPath}/index/displaybookservlet?id=${cart.bookId}">${cart.bookName}</a></span>
				</div>
				<div class='cartinfo_center_oneprice'>
					￥<span class="cartinfo_center_singleprice">${cart.bookPrice}</span>
				</div>
				<div class="cartinfo_center_mount">
					<input type="button" value="-"
						class="cartinfo_center_mountreduce">
					<input type="text" name="${cart.cartId}" value="${cart.bookMount}" class="displaybook_first_mountinput"> 
					<input type="button"
						value="+" class="cartinfo_center_mountadd">
					<input id="recmount" value="${cart.resMount}" class="cartinfo_center_resmount">
				</div>
				<div class='cartinfo_center_oneprice'>
					<span style="color: #ff0000">￥</span><span class="cartinfo_center_sumprice"><c:out value="${cart.bookPrice * cart.bookMount}"/></span>
				</div>
				<div class="cartinfo_center_delete">
					<a href="${pageContext.request.contextPath}/index/deletecartservlet?id=${cart.cartId}">删除</a>
				</div>
			</div>
			</c:forEach>
			<!-- End -->


</c:if>

		</form>
	</div>
		<jsp:include page="bottom.jsp"></jsp:include>
    <script src="../js/jquery-3.5.1.min.js"></script>
    <script>    
    $(function(){
        $(".cartinfo_center_mountadd").click(function(){
            var t = $(this).parent().find('input[class*=displaybook_first_mountinput]');
			var recmount = $(this).parent().find('input[class*=cartinfo_center_resmount]');
            
            if(parseInt(t.val())>=parseInt(recmount.val())){
                t.val(parseInt(recmount.val()));
            }else{
                t.val(parseInt(t.val())+1);
            }
       

            var p = $(this).parent().parent().children().find('span[class*=cartinfo_center_singleprice]');
            var s  = parseInt(t.val())*parseFloat(p.text());
            var f = $(this).parent().parent().find('span[class*=cartinfo_center_sumprice]');
            f.html(s.toFixed(2));
            getchbox();
            
        })

        $(".cartinfo_center_mountreduce").click(function(){
            var t = $(this).parent().find('input[class*=displaybook_first_mountinput]');
            t.val(parseInt(t.val())-1);
            if(parseInt(t.val())<1){
                t.val(1);
            }
            var p = $(this).parent().parent().children().find('span[class*=cartinfo_center_singleprice]');
            var s  = parseInt(t.val())*parseFloat(p.text());
            var f = $(this).parent().parent().find('span[class*=cartinfo_center_sumprice]');
            f.html(s.toFixed(2));
            getchbox();
        })

        $(".displaybook_first_mountinput").blur(function(){
            var reg = /^\+?[1-9][0-9]*$/;
            var t = $(this).parent().find('input[class*=displaybook_first_mountinput]');
            var recmount = $(this).parent().find('input[class*=cartinfo_center_resmount]');
            if(parseInt(t.val())>=parseInt(recmount.val())){
                t.val(parseInt(recmount.val()));
            }else if(parseInt(t.val())<=0){
                t.val(1);
            }else if(!reg.test(t)){
                t.val(parseInt(t.val()));
            }

            var p = $(this).parent().parent().children().find('span[class*=cartinfo_center_singleprice]');
            var s  = parseInt(t.val())*parseFloat(p.text());
            var f = $(this).parent().parent().find('span[class*=cartinfo_center_sumprice]');
            f.html(s.toFixed(2));
            getchbox();

        })

        function formatPrice(){
            $(".cartinfo_center_oneprice").each(function(){
                var o = $(this).find('span[class*=cartinfo_center_singleprice]');
                var n = $(this).find('span[class*=cartinfo_center_sumprice]');
                
                var fo = parseFloat($(this).find('span[class*=cartinfo_center_singleprice]').text());
                var fn = parseFloat($(this).find('span[class*=cartinfo_center_sumprice]').text());
                o.html(fo.toFixed(2));
                n.html(fn.toFixed(2));
               
            });
        }

        formatPrice();


        $(".cartinfo_center_checkbox").change(function(){
            getchbox();
        })

        function getchbox(){
            var arr = [];
            $('input[name="cartid"]').each(function(){
                var state = $(this).prop('checked');
                if(state){
                    arr.push(parseFloat($(this).parent().parent().find('span[class*=cartinfo_center_sumprice]').text()));
                }
            });
            var sum = 0;
            for(var i=0;i<arr.length;i++){
                sum += arr[i];
            }
            $("#sumPrice").html(sum.toFixed(2));
            if(arr.length==0){
                $("#submit").css({'background-color':'#848484'});
                $("#submit").attr("disabled",true);
            }else{
                $("#submit").css({'background-color':'#ff5000'});
                $("#submit").attr("disabled",false);
            }
        }

        $("#checkall").on('click',function(){
            $("input[name='cartid']").prop("checked",this.checked);
            getchbox();
        })

    })
	</script>
</body>
</html>