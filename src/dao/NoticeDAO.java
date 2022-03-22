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
import bean.Notice;

public class NoticeDAO {
	public List<Notice> sortNoticeByAddtime(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Notice> noticeList = new ArrayList<Notice>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select notice_id,name,detail,add_time from notice order by add_time desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Notice notice = new Notice();
				notice.setNoticeId(rs.getInt("notice_id"));
				notice.setName(rs.getString("name"));
				notice.setDetail(rs.getString("detail"));
				notice.setAddTime(rs.getDate("add_time"));
				noticeList.add(notice);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeList;
	}
	
	public Notice selectNotice(int noticeId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Notice notice = null;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select notice_id,name,detail,add_time from notice where notice_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, noticeId);
			rs = ps.executeQuery();
			if(rs.next()){
				notice = new Notice();
				notice.setNoticeId(rs.getInt("notice_id"));
				notice.setName(rs.getString("name"));
				notice.setDetail(rs.getString("detail"));
				notice.setAddTime(rs.getDate("add_time"));
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}
	
	
	public List<Notice> selectPageAll(int start,int recCountInPage){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Notice> noticeList = new ArrayList<Notice>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select notice_id,name,detail,add_time from notice order by notice_id limit "+(start-1)+","+recCountInPage;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Notice notice = new Notice();
				notice.setNoticeId(rs.getInt("notice_id"));
				notice.setName(rs.getString("name"));
				notice.setDetail(rs.getString("detail"));
				notice.setAddTime(rs.getDate("add_time"));
				noticeList.add(notice);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeList;
	}
	
	public int recordCount(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = BaseDAO.getConnection();
			String sql = "select count(*) as recordCount from notice";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			x = rs.getInt("recordCount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public boolean addNotice(Notice notice){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean isAdd = false;
		try {
			conn = BaseDAO.getConnection();
			String addTime = sdf.format(new Date());
			String sql = "insert into notice(name,detail,add_time) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, notice.getName());
			ps.setString(2, notice.getDetail());
			ps.setString(3, addTime);
			ps.executeUpdate();
			isAdd = true;
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAdd;
	}
	
	public boolean deleteNotice(int noticeId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isDelete = false;
		try {
			conn = BaseDAO.getConnection();
			String sql = "delete from notice where notice_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, noticeId);
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
			isDelete = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDelete;
	}
}
