package adminServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Admin;
import dao.AdminDAO;
import dao.UserDAO;

@WebServlet("/admin/adminloginservlet")
public class AdminLoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("adminname");
			String passwd = request.getParameter("password");
			AdminDAO adminDAO = new AdminDAO();
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			
			Admin a = new Admin();
			a = adminDAO.loginAdmin(name,passwd);
			if(a!=null){
				request.getSession().setAttribute("admin", a);
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
