package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import dao.BookDAO;

@WebServlet("/index/searchservlet")
public class SearchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String searchname = request.getParameter("searchname");
		BookDAO bookDao = new BookDAO();
		List<Book> bookList = bookDao.selectByName(searchname);
		if(bookList.isEmpty()){
			request.setAttribute("searchname", searchname);
			request.getRequestDispatcher("searchnull.jsp").forward(request, response);
		}else{
			request.setAttribute("searchname", searchname);
			request.setAttribute("bookList", bookList);
			request.getRequestDispatcher("search.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
