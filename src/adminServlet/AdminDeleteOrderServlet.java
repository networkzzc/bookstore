package adminServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;

@WebServlet("/admin/admindeleteorderservlet")
public class AdminDeleteOrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderCode = request.getParameter("code");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		OrderDAO orderDao = new OrderDAO();
		if(orderDao.deleteOrder(orderCode)){
			request.setAttribute("message", "É¾³ý³É¹¦£¡");
		}else{
			request.setAttribute("message", "É¾³ýÊ§°Ü£¡");
		}
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}
}
