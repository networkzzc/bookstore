package adminServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoticeDAO;

@WebServlet("/admin/admindeletenoticeservlet")
public class AdminDeleteNoticeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeId = Integer.parseInt(request.getParameter("id"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		NoticeDAO noticeDao = new NoticeDAO();
		if(noticeDao.deleteNotice(noticeId)){
			request.setAttribute("message", "É¾³ý³É¹¦£¡");
		}else{
			request.setAttribute("message", "É¾³ýÊ§°Ü£¡");
		}
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}
}
