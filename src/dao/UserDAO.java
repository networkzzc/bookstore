package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import bean.Book;
import bean.User;
import other.MD5Di;

public class UserDAO {
	public User loginUser(String username, String password) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		User user = null;
		try {

			String passwdDigest = MD5Di.md5Digest(password);
			conn = BaseDAO.getConnection();
			String sql = "select user_id,name,passwd,phone,email,sex,birth,integral from user where name=? and passwd=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, passwdDigest);

			rs = ps.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setPasswd(rs.getString("passwd"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setSex(rs.getString("sex"));
				user.setBirth(rs.getDate("birth"));
				user.setIntegral(rs.getInt("integral"));
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void registerUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			String passwdDigest = MD5Di.md5Digest(user.getPasswd());
			String birthDate = sdf.format(user.getBirth());
			conn = BaseDAO.getConnection();
			String sql = "insert into user(name,passwd,phone,email,sex,birth) values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, passwdDigest);
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getSex());
			ps.setString(6, birthDate);
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean existUser(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isExist = true;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select user_id from user where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (!rs.next()) {
				isExist = false;
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}

	public void updateUser(User user) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String birthDate = sdf.format(user.getBirth());
			conn = BaseDAO.getConnection();
			String sql = "update user set phone=?,email=?,sex=?,birth=? where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getPhone());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getSex());
			ps.setString(4, birthDate);
			ps.setString(5, user.getName());
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePassword(String username, String password) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String passwdDigest = MD5Di.md5Digest(password);
			conn = BaseDAO.getConnection();
			String sql = "update user set passwd=? where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, passwdDigest);
			ps.setString(2, username);
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String selectUserNameById(int id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String name = "";
		try {
			conn = BaseDAO.getConnection();
			String sql = "select name from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	public void addIntegral(int userId,String price) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = BaseDAO.getConnection();
			String sql = "update user set integral=integral+? where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, price);
			ps.setInt(2, userId);
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> selectPageAll(int start, int recCountInPage) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select user_id,name,phone,email,sex,birth,integral from user order by user_id limit "
					+ (start - 1) + "," + recCountInPage;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setSex(rs.getString("sex"));
				user.setBirth(rs.getDate("birth"));
				user.setIntegral(rs.getInt("integral"));
				userList.add(user);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public int recordCount() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select count(*) as recordCount from user";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			x = rs.getInt("recordCount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	public User selectUserById(int userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select user_id,name,phone,email,sex,birth,integral from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setSex(rs.getString("sex"));
				user.setBirth(rs.getDate("birth"));
				user.setIntegral(rs.getInt("integral"));
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean updateUserAll(User user) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean isUpdate = false;
		try {
			String birthDate = sdf.format(user.getBirth());
			conn = BaseDAO.getConnection();
			String sql = "update user set name=?,phone=?,email=?,sex=?,birth=?,integral=? where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPhone());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getSex());
			ps.setString(5, birthDate);
			ps.setInt(6, user.getIntegral());
			ps.setInt(7, user.getUserId());
			ps.executeUpdate();
			isUpdate = true;
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdate;
	}
	
	public boolean deleteUser(int userId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isDelete = false;
		try {
			conn = BaseDAO.getConnection();
			String sql = "delete from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
			isDelete = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDelete;
	}
	
	public User restartUser(int userId){
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		User user = null;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select user_id,name,passwd,phone,email,sex,birth,integral from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			rs = ps.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setPasswd(rs.getString("passwd"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setSex(rs.getString("sex"));
				user.setBirth(rs.getDate("birth"));
				user.setIntegral(rs.getInt("integral"));
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
