package adminServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import dao.BookDAO;

@WebServlet("/admin/adminupdatebookservlet")
public class AdminUpdateBookServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = new Book();
		BookDAO bookDao = new BookDAO();
		book = bookDao.selectById(bookId);
		
		request.setAttribute("book", book);
		request.getRequestDispatcher("updatebook.jsp").forward(request, response);
	}
}
