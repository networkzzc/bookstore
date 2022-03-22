package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.CartDAO;

@WebServlet("/index/addcartservlet")
public class AddCartServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		if(user!=null){
			int userId = user.getUserId();
			int bookId = Integer.parseInt(request.getParameter("bookid"));
			int bookMount = Integer.parseInt(request.getParameter("mount"));
			
			CartDAO cartDao = new CartDAO();
			
			if(cartDao.selectCart(bookId,user.getUserId())){
				cartDao.updateCart(bookId);
			}else{
				cartDao.addCart(bookId, bookMount, userId);
			}
			
			request.setAttribute("message", "已添加至购物车");
			request.getRequestDispatcher("displaybook.jsp").forward(request, response);
		
		}else{
			request.setAttribute("message", "请登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
