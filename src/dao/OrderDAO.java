package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Book;
import bean.Order;
import bean.OrderPrice;
import bean.ReOrder;

public class OrderDAO {
	public void addOrder(Order order){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			conn = BaseDAO.getConnection();
			String addTime = sdf.format(new Date());
			String sql = "insert into order_list(order_code,book_id,book_price,mount,user_id,receiver,address,phone,add_time,price,state) values(?,?,?,?,?,?,?,?,?,?,?)";
//			String sql = "insert into order_list(order_code) values(?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, order.getOrderCode());
			ps.setInt(2, order.getBookId());
			ps.setFloat(3, order.getBookPrice());
			ps.setInt(4, order.getMount());
			ps.setInt(5, order.getUserId());
			ps.setString(6, order.getReceiver());
			ps.setString(7, order.getAddress());
			ps.setString(8, order.getPhone());
			ps.setString(9, addTime);
			ps.setFloat(10, order.getPrice());
			ps.setString(11, "未支付");
			
//			System.out.println(order.getOrderCode()+"\t"+order.getBookId()+"\t"+order.getBookPrice()+"\t"+order.getMount()+"\t"+order.getUserId()+"\t"+order.getReceiver()+"\t"+order.getAddress()+"\t"+order.getPhone()+"\t"+addTime+"\t"+order.getPrice());
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void changeState(String orderCode){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = BaseDAO.getConnection();
			String sql = "update order_list set state='已支付' where order_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderCode);
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void reduceBookMount(String orderCode){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select book_id,mount from order_list where order_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderCode);
			rs = ps.executeQuery();
			while(rs.next()){
				int bookId = rs.getInt("book_id");
				int mount = rs.getInt("mount");
				String sql1 = "update book set mount=mount-?,sale=sale+? where book_id=?";
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps1.setInt(1, mount);
				ps1.setInt(2, mount);
				ps1.setInt(3, bookId);
				ps1.executeUpdate();
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<OrderPrice> listOrderUnPaid(int userId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderPrice> orderCodeList = new ArrayList<OrderPrice>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select distinct order_code,price,phone,address,add_time from order_list where user_id=? and state='未支付'";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				OrderPrice orderPrice = new OrderPrice();
				orderPrice.setOrderCode(rs.getString("order_code"));
				orderPrice.setSumPrice(rs.getFloat("price"));
				orderPrice.setPhone(rs.getString("phone"));
				orderPrice.setAddress(rs.getString("address"));
				orderPrice.setAddTime(rs.getDate("add_time"));
				orderCodeList.add(orderPrice);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderCodeList;
	}
	
	public List<OrderPrice> listOrderPaid(int userId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderPrice> orderCodeList = new ArrayList<OrderPrice>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select distinct order_code,price,phone,address,add_time from order_list where user_id=? and state='已支付'";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				OrderPrice orderPrice = new OrderPrice();
				orderPrice.setOrderCode(rs.getString("order_code"));
				orderPrice.setSumPrice(rs.getFloat("price"));
				orderPrice.setPhone(rs.getString("phone"));
				orderPrice.setAddress(rs.getString("address"));
				orderPrice.setAddTime(rs.getDate("add_time"));
				orderCodeList.add(orderPrice);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderCodeList;
	}
	
	public List<ReOrder> listOrderInfo(String orderCode){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ReOrder> orderList = new ArrayList<ReOrder>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select book_id,book_price,mount from order_list where order_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderCode);
			rs = ps.executeQuery();
			while(rs.next()){
				ReOrder reorder = new ReOrder();
				int bookId = rs.getInt("book_id");
				reorder.setBookId(bookId);
				reorder.setBookMount(rs.getInt("mount"));
				reorder.setBookPrice(rs.getFloat("book_price"));
				String sql1 = "select name,image_src from book where book_id=?";
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps1.setInt(1, bookId);
				ResultSet rs1 = ps1.executeQuery();
				if(rs1.next()){
					reorder.setBookName(rs1.getString("name"));
					reorder.setBookImg(rs1.getString("image_src"));
				}
				orderList.add(reorder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	public List<Order> selectPageAll(int start,int recCountInPage){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Order> orderList = new ArrayList<Order>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select distinct order_code,user_id,receiver,address,phone,add_time,price,state from order_list order by state desc limit "+(start-1)+","+recCountInPage;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Order order = new Order();
				order.setOrderCode(rs.getString("order_code"));
				order.setUserId(rs.getInt("user_id"));
				order.setReceiver(rs.getString("receiver"));
				order.setAddress(rs.getString("address"));
				order.setPhone(rs.getString("phone"));
				order.setAdd_time(rs.getDate("add_time"));
				order.setPrice(rs.getFloat("price"));
				order.setState(rs.getString("state"));
				orderList.add(order);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	public int recordCount(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select count(distinct order_code) as recordCount from order_list";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			x = rs.getInt("recordCount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public List<ReOrder> listOrderInfoPage(String orderCode,int start,int recCountInPage){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ReOrder> orderList = new ArrayList<ReOrder>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select book_id,book_price,mount from order_list where order_code=? order by order_id limit "+(start-1)+","+recCountInPage;
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderCode);
			rs = ps.executeQuery();
			while(rs.next()){
				ReOrder reorder = new ReOrder();
				int bookId = rs.getInt("book_id");
				reorder.setBookId(bookId);
				reorder.setBookMount(rs.getInt("mount"));
				reorder.setBookPrice(rs.getFloat("book_price"));
				String sql1 = "select name,image_src from book where book_id=?";
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps1.setInt(1, bookId);
				ResultSet rs1 = ps1.executeQuery();
				if(rs1.next()){
					reorder.setBookName(rs1.getString("name"));
					reorder.setBookImg(rs1.getString("image_src"));
				}
				orderList.add(reorder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	public int recordInfoCount(String orderCode){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select count(*) as recordCount from order_list where order_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderCode);
			rs = ps.executeQuery();
			rs.next();
			x = rs.getInt("recordCount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public boolean deleteOrder(String orderCode){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isDelete = false;
		try {
			conn = BaseDAO.getConnection();
			String sql = "delete from order_list where order_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderCode);
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
			isDelete = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDelete;
	}
	
	public Order selectInfoByCode(String orderCode){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Order order = null;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select distinct order_code,receiver,address,phone from order_list where order_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				order = new Order();
				order.setOrderCode(rs.getString("order_code"));
				order.setReceiver(rs.getString("receiver"));
				order.setAddress(rs.getString("address"));
				order.setPhone(rs.getString("phone"));
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	
	public boolean updateOrderInfo(Order order){
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean isUpdate = false;
		
		try{
			conn = BaseDAO.getConnection();
			String sql = "update order_list set receiver=?,address=?,phone=? where order_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, order.getReceiver());
			ps.setString(2, order.getAddress());
			ps.setString(3, order.getPhone());
			ps.setString(4, order.getOrderCode());
			ps.executeUpdate();
			isUpdate = true;
			BaseDAO.close(rs, ps, conn);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return isUpdate;
	}
}

