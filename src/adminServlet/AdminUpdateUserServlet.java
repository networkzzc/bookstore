package adminServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDAO;

@WebServlet("/admin/adminupdateuserservlet")
public class AdminUpdateUserServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = new User();
		UserDAO userDao = new UserDAO();
		
		user = userDao.selectUserById(userId);
		
		request.setAttribute("user", user);
		request.getRequestDispatcher("updateuser.jsp").forward(request, response);
	}
}
