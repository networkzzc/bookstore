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

@WebServlet("/index/updateuserservlet")
public class UpdateUserServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String sex = request.getParameter("sex");
		String birth = request.getParameter("birth");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		
		UserDAO userDao = new UserDAO();
		try {
			Date birthDate = sdf.parse(birth);
			User user = new User();
			user.setName(username);
			user.setPhone(phone);
			user.setEmail(email);
			user.setSex(sex);
			user.setBirth(birthDate);
			userDao.updateUser(user);
			
			HttpSession session = request.getSession();
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			
			User userOld = (User)session.getAttribute("user");
			if(userOld!=null){
				session.removeAttribute("user");
			}
			
			request.setAttribute("message", "信息修改成功，请重新登录");
			request.getRequestDispatcher("../index.jsp").forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

	}

}
