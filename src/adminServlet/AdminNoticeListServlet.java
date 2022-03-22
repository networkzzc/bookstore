package adminServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Notice;
import dao.NoticeDAO;

@WebServlet("/admin/adminnoticelistservlet")
public class AdminNoticeListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int recCountInPage = 10;
		int pageIndex = 1;
		int recordCount = 0;
		int pageCount = 0;
		int start = 0;
		int end = 0;
		
		List<Notice> noticeList = new ArrayList<Notice>();
		String p = request.getParameter("pageIndex");
		if(p!=null){
			pageIndex = Integer.parseInt(p);
		}
		
		NoticeDAO noticeDao = new NoticeDAO();
		recordCount = noticeDao.recordCount();
		
		pageCount = recordCount/recCountInPage+(recordCount%recCountInPage==0?0:1);
		start = (pageIndex-1)*recCountInPage+1;
		end = pageIndex*recCountInPage;
		end = end>recordCount?recordCount:end;
		
		noticeList = noticeDao.selectPageAll(start, recCountInPage);
		
		request.setAttribute("recCountInPage", recCountInPage);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("recordCount", recordCount);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("noticeList", noticeList);
		request.getRequestDispatcher("managenotice.jsp").forward(request, response);
	}
}
