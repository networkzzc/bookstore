package other;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

import bean.User;
import dao.OrderDAO;
import dao.UserDAO;

@WebServlet("/index/succeed.action")
public class PaySucceedServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String out_trade_no = request.getParameter("out_trade_no");
		String price = request.getParameter("total_amount");
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		OrderDAO orderDao = new OrderDAO();
		UserDAO userDao = new UserDAO();
		orderDao.reduceBookMount(out_trade_no);
		userDao.addIntegral(userId,price);
		orderDao.changeState(out_trade_no);
		
		session.removeAttribute("user");
		
		User nUser = new User();
		nUser = userDao.restartUser(userId);
		
		request.getSession().setAttribute("user", nUser);
		request.getRequestDispatcher("../index.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
