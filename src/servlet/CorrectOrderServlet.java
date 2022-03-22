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

import bean.Cart;
import bean.ReOrder;
import bean.User;
import dao.CartDAO;

@WebServlet("/index/correctorderservlet")
public class CorrectOrderServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String[] cartIds = request.getParameterValues("cartid");
		String[] cartMount = new String[cartIds.length];
		float discount = 1;
		float sumPrice = 0;
		
		for (int i = 0; i < cartIds.length; i++) {
			cartMount[i] = request.getParameter(cartIds[i]);
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user.getIntegral()>=2000){
			discount = 0.8f;
		}
		
		List<ReOrder> reOrderList = new ArrayList<ReOrder>();
		for(int i=0;i<cartIds.length;i++){
			ReOrder reOrder = new ReOrder();
			Cart cart = new Cart();
			CartDAO cartDao = new CartDAO();
			cart = cartDao.selectCartById(Integer.parseInt(cartIds[i]));
			reOrder.setBookId(cart.getBookId());
			reOrder.setBookImg(cart.getBookImgSrc());
			reOrder.setBookName(cart.getBookName());
			reOrder.setBookPrice(cart.getBookPrice());
			reOrder.setBookMount(Integer.parseInt(cartMount[i]));
			reOrder.setDiscount(discount);
			reOrderList.add(reOrder);
			sumPrice += (reOrder.getBookPrice() * reOrder.getBookMount() * reOrder.getDiscount());
		}
		
		request.setAttribute("sumPrice", sumPrice);
		request.setAttribute("reOrderList", reOrderList);
		request.getRequestDispatcher("/index/correctorder.jsp").forward(request, response);
	}

}
