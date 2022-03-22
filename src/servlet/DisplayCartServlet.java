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

import bean.Cart;
import bean.User;
import dao.BookDAO;
import dao.CartDAO;

@WebServlet("/index/displaycartservlet")
public class DisplayCartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		if(user!=null){
			int userId = user.getUserId();
			CartDAO cartDao = new CartDAO();
			List<Cart> cartList = new ArrayList<Cart>();
			cartList = cartDao.listCart(userId);
			
			request.setAttribute("cartList", cartList);
			request.getRequestDispatcher("cartinfo.jsp").forward(request, response);
		}else{
			request.setAttribute("message", "ÇëµÇÂ¼");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
