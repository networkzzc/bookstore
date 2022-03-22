package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.BaseDAO;
import dao.UserDAO;
import other.MD5Di;

@WebServlet("/index/loginservlet")
public class LoginServlet extends HttpServlet {
	
	String name,passwd;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			name = request.getParameter("username");
			passwd = request.getParameter("password");
			UserDAO userDAO = new UserDAO();
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			
			User u = new User();
			u = userDAO.loginUser(name,passwd);
			if(u!=null){
//				request.setAttribute("isuser", "yes");
				request.getSession().setAttribute("user", u);
				request.getRequestDispatcher("../index.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
