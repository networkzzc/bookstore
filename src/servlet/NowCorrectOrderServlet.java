package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Book;
import bean.ReOrder;
import bean.User;
import dao.BookDAO;

@WebServlet("/index/nowcorrectorderservlet")
public class NowCorrectOrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		int bookId = Integer.parseInt(request.getParameter("bookid"));
		int bookMount = Integer.parseInt(request.getParameter("mount"));
		float discount = 1;
		float sumPrice = 0;
		
		Book book = new Book();
		BookDAO bookDao = new BookDAO();
		book = bookDao.selectById(bookId);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user!=null){

			if(user.getIntegral()>=2000){
				discount = 0.8f;
			}
			
			ReOrder reOrder = new ReOrder();
			reOrder.setBookId(bookId);
			reOrder.setBookImg(book.getImageSrc());
			reOrder.setBookName(book.getName());
			reOrder.setBookPrice(book.getNewPrice());
			reOrder.setBookMount(bookMount);
			reOrder.setDiscount(discount);
			List<ReOrder> reOrderList = new ArrayList<ReOrder>();
			reOrderList.add(reOrder);
			sumPrice = reOrder.getBookPrice() * bookMount * discount;
			
			request.setAttribute("sumPrice", sumPrice);
			request.setAttribute("reOrderList", reOrderList);
			request.getRequestDispatcher("/index/correctorder.jsp").forward(request, response);
		}else{
			request.setAttribute("message", "ÇëµÇÂ¼");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
