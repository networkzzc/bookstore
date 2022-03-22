<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.alipay.api.*"%>
<%@ page import="com.alipay.api.request.*"%>
<%@ page import="java.util.*,bean.*,dao.*,other.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>付款</title>
</head>
<body>
<%
	//获得初始化的AlipayClient
	AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
	
	//设置请求参数
	AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
	alipayRequest.setReturnUrl(AlipayConfig.return_url);
	alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
	
	/*
	//商户订单号，商户网站订单系统中唯一订单号，必填
	String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
	//付款金额，必填
	String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
	//订单名称，必填
	String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
	//商品描述，可空
	String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
	*/
	
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html");
	
	User user = (User)session.getAttribute("user");
	int userId = user.getUserId();
	String receiver = request.getParameter("receiver");
	String address = request.getParameter("address");
	String phone = request.getParameter("phone");
	
	
	String out_trade_no = SetOrderCode.getCode(userId);
	String total_amount = request.getParameter("sumprice");
	String subject = "图书";
	String body = "";
	
	String[] bookIds = request.getParameterValues("bookid");
	String[] singlePrice = request.getParameterValues("singleprice");
	String[] bookMounts = request.getParameterValues("bookmount");
	for(int i=0;i<bookIds.length;i++){
		Order order = new Order();
		order.setOrderCode(out_trade_no);
		order.setBookId(Integer.parseInt(bookIds[i]));
		order.setBookPrice(Float.parseFloat(singlePrice[i]));
		order.setMount(Integer.parseInt(bookMounts[i]));
		order.setUserId(userId);
		order.setReceiver(receiver);
		order.setAddress(address);
		order.setPhone(phone);
		order.setPrice(Float.parseFloat(total_amount));
		OrderDAO orderDao = new OrderDAO();
		orderDao.addOrder(order);
	}

	
	alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
			+ "\"total_amount\":\""+ total_amount +"\"," 
			+ "\"subject\":\""+ subject +"\"," 
			+ "\"body\":\""+ body +"\"," 
			+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
	
	
	//请求
	String result = alipayClient.pageExecute(alipayRequest).getBody();
	
	//输出
	out.println(result);
%>
</body>
</html>