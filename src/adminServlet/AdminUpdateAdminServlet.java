package adminServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDAO;

@WebServlet("/admin/adminupdateadminservlet")
public class AdminUpdateAdminServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		int adminId = Integer.parseInt(request.getParameter("adminid"));
		String oldPasswd = request.getParameter("oldpassword");
		String password = request.getParameter("password");
		
		AdminDAO adminDao = new AdminDAO();
		if(adminDao.isAdmin(adminId, oldPasswd)){
			if(adminDao.changePassword(adminId, password)){
				request.setAttribute("message", "ĞŞ¸ÄÃÜÂë³É¹¦");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "ĞŞ¸ÄÃÜÂëÊ§°Ü");
				request.getRequestDispatcher("updateadmin.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("message", "¾ÉÃÜÂë´íÎó£¬ÇëÖØĞÂÊäÈë");
			request.getRequestDispatcher("updateadmin.jsp").forward(request, response);
		}
		
	}
}
