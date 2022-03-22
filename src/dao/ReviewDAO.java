package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Review;

public class ReviewDAO {
	public List<Review> selectByBookId(int bookId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Review> reviewList = new ArrayList<Review>();
		try {
			conn = BaseDAO.getConnection();
			String sql = "select user_id,grade,detail,add_time from review where book_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			rs = ps.executeQuery();
			while(rs.next()){
				Review review = new Review();
				review.setUserId(rs.getInt("user_id"));
				review.setGrade(rs.getInt("grade"));
				review.setDetail(rs.getString("detail"));
				review.setAddTime(rs.getDate("add_time"));
				reviewList.add(review);
			}
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviewList;
	}
	
	public void addReview(Review review){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			conn = BaseDAO.getConnection();
			String addTime = sdf.format(new Date());
			String sql = "insert into review(book_id,user_id,grade,detail,add_time) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getBookId());
			ps.setInt(2, review.getUserId());
			ps.setInt(3, review.getGrade());
			ps.setString(4, review.getDetail());
			ps.setString(5, addTime);
			ps.executeUpdate();
			BaseDAO.close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
