package adminServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Order;
import dao.OrderDAO;

@WebServlet("/admin/adminupdateordercorrectservlet")
public class AdminUpdateOrderCorrectServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String orderCode = request.getParameter("ordercode");
		String receiver = request.getParameter("receiver");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		Order order = new Order();
		order.setOrderCode(orderCode);
		order.setReceiver(receiver);
		order.setAddress(address);
		order.setPhone(phone);
		
		OrderDAO orderDao = new OrderDAO();
		
		if(orderDao.updateOrderInfo(order)){
			request.setAttribute("message", "修改订单成功");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}else{
			request.setAttribute("message", "修改订单失败");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
	}
}
