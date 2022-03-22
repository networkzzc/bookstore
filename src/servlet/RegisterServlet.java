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

import bean.User;
import dao.UserDAO;

@WebServlet("/index/registerservlet")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String sex = request.getParameter("sex");
		String birth = request.getParameter("birth");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		
		UserDAO userDao = new UserDAO();
		if(userDao.existUser(username)){
			request.setAttribute("message", "该用户已存在，请重新注册！");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}else{
			try {
				Date birthDate = sdf.parse(birth);
				User user = new User();
				user.setName(username);
				user.setPasswd(password);
				user.setPhone(phone);
				user.setEmail(email);
				user.setSex(sex);
				user.setBirth(birthDate);
				userDao.registerUser(user);
				request.setAttribute("message", "注册成功，请登录");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

}
