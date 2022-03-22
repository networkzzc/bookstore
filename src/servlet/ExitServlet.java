package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;

@WebServlet("/index/exitservlet")
public class ExitServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
			
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		User user = (User)session.getAttribute("user");
		if(user!=null){
			session.removeAttribute("user");
		}
		request.getRequestDispatcher("../index.jsp").forward(request, response);
	}


}
