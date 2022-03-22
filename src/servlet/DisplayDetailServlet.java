package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Notice;
import dao.NoticeDAO;

@WebServlet("/index/displaydetailservlet")
public class DisplayDetailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Notice notice = new Notice();
		NoticeDAO noticeDao = new NoticeDAO();
		
		notice = noticeDao.selectNotice(id);
		
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("noticeinfo.jsp").forward(request, response);
	}

}
