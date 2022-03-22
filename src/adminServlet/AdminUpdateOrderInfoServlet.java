package adminServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Order;
import dao.OrderDAO;

@WebServlet("/admin/adminupdateorderinfoservlet")
public class AdminUpdateOrderInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderCode = request.getParameter("code");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		Order order = new Order();
		OrderDAO orderDao = new OrderDAO();
		
		order = orderDao.selectInfoByCode(orderCode);
		
		request.setAttribute("order", order);
		request.getRequestDispatcher("updateorder.jsp").forward(request, response);
	}
}
