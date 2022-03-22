package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import bean.Review;
import dao.BookDAO;
import dao.ReviewDAO;

@WebServlet("/index/displaybookservlet")
public class DisplayBookServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int id = Integer.parseInt(request.getParameter("id"));
		BookDAO BookDao = new BookDAO();
		Book book = new Book();
		book = BookDao.selectById(id);
		
		String bookType = BookDao.findBookType(book.getTypeId());
		
		ReviewDAO reviewDao = new ReviewDAO();
		List<Review> reviewList = new ArrayList<Review>();
		reviewList = reviewDao.selectByBookId(id);
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		request.getSession().setAttribute("book", book);
		request.setAttribute("bookType", bookType);
		request.setAttribute("reviewList", reviewList);
		request.getRequestDispatcher("displaybook.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
