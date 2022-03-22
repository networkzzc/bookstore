<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,bean.*,dao.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="cartinfo_center_div">
        <div>
            <span style="margin-left: 20px; font-size: 18px;">我的订单</span>
            <div class="cartinfo_center_underline"></div>
        </div>

        <!-- 未支付 -->
        <div class="displayorder_div">
            <span class="displayorder_paid_span">未支付订单</span>
            <c:choose>
            <c:when test="${empty unPaidList}">
            <p style="margin-left: 30px; font-size: 12px; color: #848484">您没有未支付的订单</p>
            </c:when>
            <c:otherwise>
            <!-- Begin -->
            <c:forEach items="${unPaidList}" var="unpaid">
            <div class="displayorder_item">
                <div class="displayorder_item_title">订单号：<span>${unpaid.orderCode}</span><span>&nbsp;&nbsp;&nbsp;&nbsp;${unpaid.addTime}</span></div>
                
                <div style="float: left;">
                
                <%
                	List<ReOrder> orderList = new ArrayList<ReOrder>();
                	OrderDAO orderDao = new OrderDAO();
                	OrderPrice op = (OrderPrice)pageContext.findAttribute("unpaid");
                	orderList = orderDao.listOrderInfo(op.getOrderCode());
                	request.setAttribute("orderList", orderList);
                %>
                 <!-- B -->
                 <c:forEach var="order" items="${orderList}">
                 <div class="displayorder_item_itemu">
                    <div class="displayorder_item_img">
                        <img src="${pageContext.request.contextPath}/${order.bookImg}" height="80px">
                    </div>
                    <div class="displayorder_item_name">
                        <span><a href="${pageContext.request.contextPath}/index/displaybookservlet?id=${order.bookId}" target="_blank">${order.bookName}</a></span>
                    </div>
                    <div class="displayorder_item_price">
                        <span><fmt:formatNumber type="currency">${order.bookPrice}</fmt:formatNumber></span>
                    </div>
                    <div class="displayorder_item_price">
                        <span>${order.bookMount}</span>
                    </div>
                    
                </div>
                </c:forEach>
                <!-- E -->
                </div>
                <div class="displayorder_item_right">
                    <span><fmt:formatNumber type="currency">${unpaid.sumPrice}</fmt:formatNumber></span>
                    <p>${unpaid.phone}</p>
                    <p>${unpaid.address}</p>
                </div>
                <div class="displayorder_item_price">
                        <span><a href="${pageContext.request.contextPath}/index/alipay.trade.page.repay.jsp?ordercode=${unpaid.orderCode}&sumprice=${unpaid.sumPrice}">去支付</a></span>
                    </div>
            </div>
			</c:forEach>
            <!-- End -->
            </c:otherwise>
            </c:choose>
            
        </div>


        <!-- 已支付 -->
        <div class="displayorder_div">
            <span class="displayorder_paid_span">已支付订单</span>
            <c:choose>
            <c:when test="${empty paidList}">
            <p style="margin-left: 30px; font-size: 12px; color: #848484">您没有已支付的订单</p>
            </c:when>
            <c:otherwise>
             <!-- Begin -->
            <c:forEach items="${paidList}" var="paid">
            <div class="displayorder_item">
                <div class="displayorder_item_title">订单号：<span>${paid.orderCode}</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>${paid.addTime}</span></div>
                
                <div style="float: left;">
                <%
                	List<ReOrder> paidOrderList = new ArrayList<ReOrder>();
                	OrderDAO orderDao = new OrderDAO();
                	OrderPrice opp = (OrderPrice)pageContext.findAttribute("paid");
                	paidOrderList = orderDao.listOrderInfo(opp.getOrderCode());
                	request.setAttribute("paidOrderList", paidOrderList);
                %>
                <!-- B -->
                <c:forEach var="paidOrder" items="${paidOrderList}">
                <div class="displayorder_item_item">
                    <div class="displayorder_item_img">
                        <img src="${pageContext.request.contextPath}/${paidOrder.bookImg}" height="80px">
                    </div>
                    <div class="displayorder_item_name">
                        <span><a href="${pageContext.request.contextPath}/index/displaybookservlet?id=${paidOrder.bookId}" target="_blank">${paidOrder.bookName}</a></span>
                    </div>
                    <div class="displayorder_item_price">
                        <span><fmt:formatNumber type="currency">${paidOrder.bookPrice}</fmt:formatNumber></span>
                    </div>
                    <div class="displayorder_item_price">
                        <span>${paidOrder.bookMount}</span>
                    </div>
                    <div class="displayorder_item_price">
                        <span><a href="${pageContext.request.contextPath}/index/addreview.jsp?id=${paidOrder.bookId}">评价</a></span>
                    </div>
                </div>
                </c:forEach>
                <!-- E -->
                
                </div>
                <div class="displayorder_item_right">
                    <span><fmt:formatNumber type="currency">${paid.sumPrice}</fmt:formatNumber></span>
                    <p>${paid.phone}</p>
                    <p>${paid.address}</p>
                </div>
            </div>
            </c:forEach>
            <!-- End -->
            </c:otherwise>
            </c:choose>
           
        </div>

    </div>
        <jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>