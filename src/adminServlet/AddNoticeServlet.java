package adminServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Notice;
import dao.NoticeDAO;

@WebServlet("/admin/addnoticeservlet")
public class AddNoticeServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		
		Notice notice = new Notice();
		notice.setName(title);
		notice.setDetail(detail);
		
		NoticeDAO noticeDao = new NoticeDAO();
		if(noticeDao.addNotice(notice)){
			request.setAttribute("message", "添加日志成功");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}else{
			request.setAttribute("message", "添加日志失败");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
	}

}
