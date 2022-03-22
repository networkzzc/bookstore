package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderPrice;
import bean.User;
import dao.OrderDAO;

@WebServlet("/index/displayorderservlet")
public class DisplayOrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		OrderDAO orderDao = new OrderDAO();
		
		List<OrderPrice> unPaidList = new ArrayList<OrderPrice>();
		List<OrderPrice> paidList = new ArrayList<OrderPrice>();
		unPaidList = orderDao.listOrderUnPaid(user.getUserId());
		paidList = orderDao.listOrderPaid(user.getUserId());
		
		request.setAttribute("unPaidList", unPaidList);
		request.setAttribute("paidList", paidList);
		request.getRequestDispatcher("displayorder.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
