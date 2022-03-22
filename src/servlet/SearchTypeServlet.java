package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import dao.BaseDAO;
import dao.BookDAO;

@WebServlet("/index/searchtypeservlet")
public class SearchTypeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int typeId = Integer.parseInt(request.getParameter("type"));
		BookDAO bookDao = new BookDAO();
		List<Book> bookList = new ArrayList<Book>();
		bookList = bookDao.selectByType(typeId);
		String searchname = "";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select name from book_type where type_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, typeId);
			rs = ps.executeQuery();
			if(rs.next()){
				searchname = rs.getString("name");
			}
			BaseDAO.close(rs, ps, conn);
			
			if(bookList.isEmpty()){
				request.setAttribute("searchname", searchname);
				request.getRequestDispatcher("searchnull.jsp").forward(request, response);
			}else{
				request.setAttribute("searchname", searchname);
				request.setAttribute("bookList", bookList);
				request.getRequestDispatcher("search.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
