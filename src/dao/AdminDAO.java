package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bean.Admin;
import bean.User;
import other.MD5Di;

public class AdminDAO {
	public Admin loginAdmin(String adminname, String password) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Admin admin = null;
		try {

			String passwdDigest = MD5Di.md5Digest(password);
			conn = BaseDAO.getConnection();
			String sql = "select admin_id,name,passwd from admin where name=? and passwd=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, adminname);
			ps.setString(2, passwdDigest);

			rs = ps.executeQuery();
			
			while(rs.next()){
				admin = new Admin();
				admin.setAdminId(rs.getInt("admin_id"));
				admin.setName(rs.getString("name"));
				admin.setPasswd(rs.getString("passwd"));
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}
	
	public List<Admin> selectPageAll(int start, int recCountInPage) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Admin> adminList = new ArrayList<Admin>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select admin_id,name from admin order by admin_id limit "
					+ (start - 1) + "," + recCountInPage;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setAdminId(rs.getInt("admin_id"));
				admin.setName(rs.getString("name"));
				adminList.add(admin);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adminList;
	}
	
	public int recordCount() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select count(*) as recordCount from admin";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			x = rs.getInt("recordCount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public boolean addAdmin(Admin admin) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isAdd = false;
		try {
			String passwdDigest = MD5Di.md5Digest(admin.getPasswd());
			conn = BaseDAO.getConnection();
			String sql = "insert into admin(name,passwd) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, admin.getName());
			ps.setString(2, passwdDigest);
			ps.executeUpdate();
			isAdd = true;
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAdd;
	}
	
	public boolean existAdmin(String adminname) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isExist = true;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select admin_id from admin where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, adminname);
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
	
	public boolean deleteAdmin(int adminId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isDelete = false;
		try {
			conn = BaseDAO.getConnection();
			String sql = "delete from admin where admin_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, adminId);
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
			isDelete = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDelete;
	}
	
	public boolean changePassword(int adminId,String passwd){
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean isUpdate = false;
		try {
			String passwdDigest = MD5Di.md5Digest(passwd);
			conn = BaseDAO.getConnection();
			String sql = "update admin set passwd=? where admin_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, passwdDigest);
			ps.setInt(2, adminId);
			ps.executeUpdate();
			isUpdate = true;
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdate;
	}
	
	public boolean isAdmin(int adminId,String passwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isExist = false;
		try {
			String passwdDigest = MD5Di.md5Digest(passwd);
			conn = BaseDAO.getConnection();
			String sql = "select admin_id from admin where admin_id=? and passwd=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, adminId);
			ps.setString(2, passwdDigest);
			rs = ps.executeQuery();
			if (rs.next()) {
				isExist = true;
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}
}
