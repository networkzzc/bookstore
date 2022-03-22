package adminServlet;

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

@WebServlet("/admin/adminupdateusercorrectservlet")
public class AdminUpdateUserCorrectServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");		
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			int userId = Integer.parseInt(request.getParameter("userid"));
			String userName = request.getParameter("username");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String sex = request.getParameter("sex");
			String birth = request.getParameter("birth");
			Date userBirth = sdf.parse(birth);
			int integral = Integer.parseInt(request.getParameter("integral"));
			
			User user = new User();
			user.setUserId(userId);
			user.setName(userName);
			user.setPhone(phone);
			user.setEmail(email);
			user.setSex(sex);
			user.setBirth(userBirth);
			user.setIntegral(integral);
			
			UserDAO userDao = new UserDAO();
			
			if(userDao.updateUserAll(user)){
				request.setAttribute("message", "修改用户成功");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "修改用户失败");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
