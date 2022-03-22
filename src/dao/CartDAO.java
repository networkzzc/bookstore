package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Cart;

public class CartDAO {
	
	public List<Cart> listCart(int userId){
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Cart> cartList = new ArrayList<Cart>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select cart_id,book_id,book_mount,user_id,add_time from cart where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				Cart cart = new Cart();
				cart.setCartId(rs.getInt("cart_id"));
				cart.setBookId(rs.getInt("book_id"));
				cart.setBookMount(rs.getInt("book_mount"));
				cart.setUserId(rs.getInt("user_id"));
				cart.setAddTime(rs.getDate("add_time"));
				String namesql = "select name,mount,new_price,image_src from book where book_id=?";
				PreparedStatement ps1 = conn.prepareStatement(namesql);
				ps1.setInt(1, cart.getBookId());
				ResultSet rs1 = ps1.executeQuery();
				String bookName = "";
				int resMount = 0;
				float bookPrice=0;
				String imgSrc="";
				if(rs1.next()){
					bookName = rs1.getString("name");
					resMount = rs1.getInt("mount");
					bookPrice = rs1.getFloat("new_price");
					imgSrc = rs1.getString("image_src");
				}
				cart.setBookName(bookName);
				cart.setResMount(resMount);
				cart.setBookPrice(bookPrice);
				cart.setBookImgSrc(imgSrc);
				cartList.add(cart);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartList;
	}
	
	public void addCart(int bookId,int bookMount,int userId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			conn = BaseDAO.getConnection();
			String addTime = sdf.format(new Date());
			String sql = "insert into cart(book_id,book_mount,user_id,add_time) values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setInt(2, bookMount);
			ps.setInt(3, userId);
			ps.setString(4, addTime);
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean selectCart(int bookId,int userId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isCart = false;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select book_id from cart where book_id=? and user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setInt(2, userId);
			rs = ps.executeQuery();
			if(rs.next()){
				isCart = true;
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isCart;
	}
	
	public Cart selectCartById(int cartId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cart cart = null;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select book_id from cart where cart_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cartId);
			rs = ps.executeQuery();
			if(rs.next()){
				cart = new Cart();
				cart.setBookId(rs.getInt("book_id"));
				String namesql = "select name,new_price,image_src from book where book_id=?";
				PreparedStatement ps1 = conn.prepareStatement(namesql);
				ps1.setInt(1, cart.getBookId());
				ResultSet rs1 = ps1.executeQuery();
				String bookName = "";
				String bookImg = "";
				float bookPrice = 0;
				if(rs1.next()){
					bookName = rs1.getString("name");
					bookImg = rs1.getString("image_src");
					bookPrice = rs1.getFloat("new_price");
				}
				cart.setBookName(bookName);
				cart.setBookImgSrc(bookImg);
				cart.setBookPrice(bookPrice);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cart;
	}
	
	public void updateCart(int bookId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = BaseDAO.getConnection();
			String sql = "update cart set book_mount=book_mount+1 where book_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean deleteCart(int cartId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = BaseDAO.getConnection();
			String sql = "delete from cart where cart_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cartId);
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CartDAO cd = new CartDAO();
		List<Cart> ca = cd.listCart(1);
		for(int i=0;i<ca.size();i++){
			Cart cart = new Cart();
			cart = ca.get(i);
			System.out.println(cart.getCartId());
			System.out.println(cart.getBookId());
			System.out.println(cart.getBookName());
			System.out.println(cart.getBookImgSrc());
			System.out.println(cart.getBookMount());
			System.out.println(cart.getBookPrice());
			System.out.println(cart.getUserId());
			System.out.println(cart.getAddTime());
		}
	}

}
