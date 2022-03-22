package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDAO;

@WebServlet("/index/upuserpasswdservlet")
public class UpUserPasswdServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String oldPassword = request.getParameter("oldpassword");
		String password = request.getParameter("password");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
			
		UserDAO userDao = new UserDAO();
		
		User u = new User();
		u = userDao.loginUser(username,oldPassword);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		if(u!=null){
			userDao.updatePassword(username, password);
			
			HttpSession session = request.getSession();
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			
			User userOld = (User)session.getAttribute("user");
			if(userOld!=null){
				session.removeAttribute("user");
			}
			
			request.setAttribute("message", "密码修改成功，请重新登录");
			request.getRequestDispatcher("../index.jsp").forward(request, response);
		}else{
			request.setAttribute("message", "旧密码错误，请重新输入");
			request.getRequestDispatcher("changepasswd.jsp").forward(request, response);
		}
		
		
	}

}
