package adminServlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.Book;
import dao.BookDAO;

@WebServlet("/admin/adminupdatecorrectservlet")
@MultipartConfig
public class AdminUpdateCorrectServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");		
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			int bookId = Integer.parseInt(request.getParameter("bookid"));
			String bookName = request.getParameter("bookname");
			int bookMount = Integer.parseInt(request.getParameter("mount"));
			String bookAuthor = request.getParameter("author");
			int typeId = Integer.parseInt(request.getParameter("type"));
			String bookISBN = request.getParameter("isbn");
			float oldPrice = Float.parseFloat(request.getParameter("oldprice"));
			float newPrice = Float.parseFloat(request.getParameter("newprice"));
			String bookDetail = request.getParameter("detail");		
			String bookPublisher = request.getParameter("publisher");
			String pubTime = request.getParameter("pubtime");
			Date bookPubTime = sdf.parse(pubTime);
			String bookSize = request.getParameter("size");
			String bookPaper = request.getParameter("paper");
			String bookPackage = request.getParameter("bookpackage");
			String bookSuit = request.getParameter("suit");
			String bookImageSrc = request.getParameter("imagesrc");
			String bookRereadSrc = request.getParameter("rereadsrc");
			
			Book book = new Book();
			book.setBookId(bookId);
			book.setName(bookName);
			book.setMount(bookMount);
			book.setAuthor(bookAuthor);
			book.setTypeId(typeId);
			book.setISBN(bookISBN);
			book.setOldPrice(oldPrice);
			book.setNewPrice(newPrice);
			book.setDetail(bookDetail);
			book.setPublisher(bookPublisher);
			book.setPubTime(bookPubTime);
			book.setSize(bookSize);
			book.setPaper(bookPaper);
			book.setBookPackage(bookPackage);
			book.setSuit(bookSuit);
			
			Part imgPart = request.getPart("bookimg");
			if(imgPart.getSize()>0){
				String realImgPath = request.getServletContext().getRealPath("/bookimg");
				String imgName = System.currentTimeMillis()+".jpg";
				imgPart.write(realImgPath+"/"+imgName);
				book.setImageSrc("bookimg/"+imgName);
			}else{
				book.setImageSrc(bookImageSrc);
			}
			
			Part rereadPart = request.getPart("bookreread");
			if(rereadPart.getSize()>0){
				String realRereadPath = request.getServletContext().getRealPath("/reread");
				String rereadName = System.currentTimeMillis()+".pdf";
				rereadPart.write(realRereadPath+"/"+rereadName);
				book.setRereadSrc("reread/"+rereadName);
			}else{
				book.setRereadSrc(bookRereadSrc);
			}
			
			BookDAO bookDao = new BookDAO();
			if(bookDao.updateBook(book)){
				request.setAttribute("message", "修改图书成功");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "修改图书失败");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
