package adminServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.User;

@WebServlet("/admin/adminexitservlet")
public class AdminExitServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		Admin admin = (Admin) session.getAttribute("admin");
		if (admin != null) {
			session.removeAttribute("admin");
		}
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
