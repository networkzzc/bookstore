package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Review;
import bean.User;
import dao.ReviewDAO;

@WebServlet("/index/subreviewservlet")
public class SubReviewServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		int userId = 0;
		
		if(request.getParameterValues("annoymous")==null){
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			userId = user.getUserId();
		}
		
		int bookId = Integer.parseInt(request.getParameter("bookid"));
		int grade = Integer.parseInt(request.getParameter("grade"));
		String detail = request.getParameter("detail");
		
		Review review = new Review();
		review.setUserId(userId);
		review.setBookId(bookId);
		review.setGrade(grade);
		review.setDetail(detail);
		
		ReviewDAO reviewDao = new ReviewDAO();
		reviewDao.addReview(review);
		
		request.setAttribute("message", "∆¿º€ÕÍ≥…");
		request.getRequestDispatcher("../index.jsp").forward(request, response);
	}

}
