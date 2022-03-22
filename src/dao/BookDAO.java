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
import bean.Type;

public class BookDAO {
	public List<Book> selectAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select name,author,new_price,image_src from book";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setNewPrice(rs.getFloat("new_price"));
				book.setImageSrc(rs.getString("image_src"));
				bookList.add(book);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	public List<Book> selectByName(String name) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select book_id,name,author,old_price,new_price,image_src,publisher,pub_time from book where name like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setOldPrice(rs.getFloat("old_price"));
				book.setNewPrice(rs.getFloat("new_price"));
				book.setImageSrc(rs.getString("image_src"));
				book.setPublisher(rs.getString("publisher"));
				book.setPubTime(rs.getDate("pub_time"));
				bookList.add(book);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	
	public List<Book> selectByNamePageAll(String name,int start,int recCountInPage){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select book_id,name,author,old_price,new_price,image_src,publisher,pub_time from book where name like ? order by book_id limit "+(start-1)+","+recCountInPage;
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			rs = ps.executeQuery();
			while(rs.next()){
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setOldPrice(rs.getFloat("old_price"));
				book.setNewPrice(rs.getFloat("new_price"));
				book.setImageSrc(rs.getString("image_src"));
				book.setPublisher(rs.getString("publisher"));
				book.setPubTime(rs.getDate("pub_time"));
				bookList.add(book);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	public int bookRecordCount(String name){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select count(*) as recordCount from book where name like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			rs = ps.executeQuery();
			rs.next();
			x = rs.getInt("recordCount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	

	public List<Book> selectByType(int typeId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select book_id,name,author,old_price,new_price,image_src,publisher,pub_time from book where type_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, typeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setOldPrice(rs.getFloat("old_price"));
				book.setNewPrice(rs.getFloat("new_price"));
				book.setImageSrc(rs.getString("image_src"));
				book.setPublisher(rs.getString("publisher"));
				book.setPubTime(rs.getDate("pub_time"));
				bookList.add(book);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	

	public List<Book> sortBookByAddtime() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select book_id,name,new_price,image_src,add_time from book order by add_time desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setName(rs.getString("name"));
				book.setNewPrice(rs.getFloat("new_price"));
				book.setImageSrc(rs.getString("image_src"));
				book.setAddTime(rs.getDate("add_time"));
				bookList.add(book);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	public List<Book> sortBookBySale() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select book_id,name,new_price,image_src,sale from book order by sale desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setName(rs.getString("name"));
				book.setNewPrice(rs.getFloat("new_price"));
				book.setImageSrc(rs.getString("image_src"));
				book.setSale(rs.getInt("sale"));
				bookList.add(book);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	public Book selectById(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select book_id,name,mount,author,type_id,ISBN,old_price,new_price,detail,image_src,reread_src,sale,publisher,pub_time,add_time,size,paper,book_package,suit from book where book_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setName(rs.getString("name"));
				book.setMount(rs.getInt("mount"));
				book.setAuthor(rs.getString("author"));
				book.setTypeId(rs.getInt("type_id"));
				book.setISBN(rs.getString("ISBN"));
				book.setOldPrice(rs.getFloat("old_price"));
				book.setNewPrice(rs.getFloat("new_price"));
				book.setDetail(rs.getString("detail"));
				book.setImageSrc(rs.getString("image_src"));
				book.setRereadSrc(rs.getString("reread_src"));
				book.setSale(rs.getInt("sale"));
				book.setPublisher(rs.getString("publisher"));
				book.setPubTime(rs.getDate("pub_time"));
				book.setAddTime(rs.getDate("add_time"));
				book.setSize(rs.getString("size"));
				book.setPaper(rs.getString("paper"));
				book.setBookPackage(rs.getString("book_package"));
				book.setSuit(rs.getString("suit"));
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public String findBookType(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String typeName = "";
		String typeFatherName = "";
		try {
			conn = BaseDAO.getConnection();
			String sql = "select book_type.name,type_root.name from book_type,type_root where book_type.type_id=? and book_type.father_id=type_root.type_root_id";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				typeName = rs.getString("book_type.name");
				typeFatherName = rs.getString("type_root.name");
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return typeFatherName + ">" + typeName;
	}

	public List<Type> listType() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Type> typeList = new ArrayList<Type>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select type_id,name from book_type";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Type type = new Type();
				type.setTypeId(rs.getInt("type_id"));
				type.setTypeName(rs.getString("name"));
				typeList.add(type);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return typeList;
	}

	public int selectMount(int bookId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int mount = 0;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select mount from book where book_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			rs = ps.executeQuery();
			if (rs.next()) {
				mount = rs.getInt("mount");
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mount;
	}

	public boolean addBook(Book book) {
		boolean isAdd = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			String addTime = sdf.format(new Date());
			String pubTime = sdf.format(book.getPubTime());
			conn = BaseDAO.getConnection();
			String sql = "insert into book(name,mount,author,type_id,ISBN,old_price,new_price,detail,image_src,reread_src,publisher,pub_time,add_time,size,paper,book_package,suit) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, book.getName());
			ps.setInt(2, book.getMount());
			ps.setString(3, book.getAuthor());
			ps.setInt(4, book.getTypeId());
			ps.setString(5, book.getISBN());
			ps.setFloat(6, book.getOldPrice());
			ps.setFloat(7, book.getNewPrice());
			ps.setString(8, book.getDetail());
			ps.setString(9, book.getImageSrc());
			ps.setString(10, book.getRereadSrc());
			ps.setString(11, book.getPublisher());
			ps.setString(12, pubTime);
			ps.setString(13, addTime);
			ps.setString(14,book.getSize());
			ps.setString(15, book.getPaper());
			ps.setString(16, book.getBookPackage());
			ps.setString(17, book.getSuit());
			ps.executeUpdate();
			isAdd = true;
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isAdd;
	}
	
	public List<Book> selectPageAll(int start,int recCountInPage){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select book_id,name,mount,author,type_id,ISBN,old_price,new_price,detail,image_src,reread_src,sale,publisher,pub_time,add_time,size,paper,book_package,suit from book order by book_id limit "+(start-1)+","+recCountInPage;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setName(rs.getString("name"));
				book.setMount(rs.getInt("mount"));
				book.setAuthor(rs.getString("author"));
				book.setTypeId(rs.getInt("type_id"));
				book.setISBN(rs.getString("ISBN"));
				book.setOldPrice(rs.getFloat("old_price"));
				book.setNewPrice(rs.getFloat("new_price"));
				book.setDetail(rs.getString("detail"));
				book.setImageSrc(rs.getString("image_src"));
				book.setRereadSrc(rs.getString("reread_src"));
				book.setSale(rs.getInt("sale"));
				book.setPublisher(rs.getString("publisher"));
				book.setPubTime(rs.getDate("pub_time"));
				book.setAddTime(rs.getDate("add_time"));
				book.setSize(rs.getString("size"));
				book.setPaper(rs.getString("paper"));
				book.setBookPackage(rs.getString("book_package"));
				book.setSuit(rs.getString("suit"));
				bookList.add(book);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	public int recordCount(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select count(*) as recordCount from book";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			x = rs.getInt("recordCount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public boolean updateBook(Book book){
		boolean isUpdate = false;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			conn = BaseDAO.getConnection();
			String pubTime = sdf.format(book.getPubTime());
			String sql = "update book set name=?,mount=?,author=?,type_id=?,ISBN=?,old_price=?,new_price=?,detail=?,image_src=?,reread_src=?,publisher=?,pub_time=?,size=?,paper=?,book_package=?,suit=? where book_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, book.getName());
			ps.setInt(2, book.getMount());
			ps.setString(3, book.getAuthor());
			ps.setInt(4, book.getTypeId());
			ps.setString(5, book.getISBN());
			ps.setFloat(6, book.getOldPrice());
			ps.setFloat(7, book.getNewPrice());
			ps.setString(8, book.getDetail());
			ps.setString(9, book.getImageSrc());
			ps.setString(10, book.getRereadSrc());
			ps.setString(11, book.getPublisher());
			ps.setString(12, pubTime);
			ps.setString(13, book.getSize());
			ps.setString(14, book.getPaper());
			ps.setString(15, book.getBookPackage());
			ps.setString(16, book.getSuit());
			ps.setInt(17, book.getBookId());
			ps.executeUpdate();
			isUpdate = true;
			BaseDAO.close(rs, ps, conn);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return isUpdate;
	}
	
	public boolean deleteBook(int bookId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isDelete = false;
		try {
			conn = BaseDAO.getConnection();
			String sql = "delete from book where book_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
			isDelete = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDelete;
	}

	public static void main(String[] args) {
		BookDAO bk = new BookDAO();
		System.out.println(bk.findBookType(1));
	}
}
