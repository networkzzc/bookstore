package adminServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ReOrder;
import dao.OrderDAO;

@WebServlet("/admin/adminorderinfolistservlet")
public class AdminOrderInfoListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderCode = request.getParameter("code");
		final int recCountInPage = 10;
		int pageIndex = 1;
		int recordCount = 0;
		int pageCount = 0;
		int start = 0;
		int end = 0;
		
		List<ReOrder> reOrderList = new ArrayList<ReOrder>();
		String p = request.getParameter("pageIndex");
		if(p!=null){
			pageIndex = Integer.parseInt(p);
		}
		
		OrderDAO orderDao = new OrderDAO();
		recordCount = orderDao.recordInfoCount(orderCode);
		
		pageCount = recordCount/recCountInPage+(recordCount%recCountInPage==0?0:1);
		start = (pageIndex-1)*recCountInPage+1;
		end = pageIndex*recCountInPage;
		end = end>recordCount?recordCount:end;
		
		reOrderList = orderDao.listOrderInfoPage(orderCode, start, recCountInPage);
	
		request.setAttribute("recCountInPage", recCountInPage);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("recordCount", recordCount);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("reOrderList", reOrderList);
		request.getRequestDispatcher("orderinfo.jsp").forward(request, response);
	}
}
