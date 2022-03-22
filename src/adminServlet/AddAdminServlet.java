package adminServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Admin;
import dao.AdminDAO;

@WebServlet("/admin/addadminservlet")
public class AddAdminServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String adminName = request.getParameter("adminname");
		String password = request.getParameter("password");
		AdminDAO adminDao = new AdminDAO();

		if (adminDao.existAdmin(adminName)) {
			request.setAttribute("message", "该管理员已存在，请重新添加！");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		} else {

			Admin admin = new Admin();
			admin.setName(adminName);
			admin.setPasswd(password);

			if (adminDao.addAdmin(admin)) {
				request.setAttribute("message", "添加管理员成功");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "添加管理员失败");
				request.getRequestDispatcher("addadmin.jsp").forward(request, response);
			}
		}
	}
}
