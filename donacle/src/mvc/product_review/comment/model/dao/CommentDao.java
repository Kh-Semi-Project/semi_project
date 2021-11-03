package mvc.product_review.comment.model.dao;

import static mvc.sale_product.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mvc.product_review.comment.model.vo.Comment;
import mvc.product_review.review.model.exception.ReviewException;

public class CommentDao {
	
	private Properties prop = new Properties(); 

	public CommentDao() {
		String filepath = CommentDao.class.getResource("/sql/product_review/product_review-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Comment> selectCommentList(Connection conn, int reviewNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCommentList");
		
		List<Comment> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Comment comment = new Comment();
				
				comment.setCommentsDate(rset.getString("COMMENTS_DATE"));
				comment.setCommentsDeleteYn(rset.getString("COMMENTS_DELETE_YN"));
				comment.setCommentsNo(rset.getInt("COMMENTS_NO"));
				comment.setCommentsTitle(rset.getString("COMMENTS_CONTENT"));
				comment.setCommentsType(rset.getString("COMMENTS_TYPE"));
				comment.setpCommentsNo(rset.getInt("P_COMMENTS_NO"));
				comment.setId(rset.getString("ID"));
				comment.setName(rset.getString("NAME"));
				comment.setReviewNo(reviewNo);
				
				list.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int insertComment(Connection conn, Comment comment) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertComment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getReviewNo());
			pstmt.setString(2, comment.getCommentsType());
			pstmt.setString(3, comment.getCommentsTitle());
			pstmt.setInt(4, comment.getpCommentsNo());
			pstmt.setString(5, comment.getId());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ReviewException("후기 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int updateComment(Connection conn, Comment comment) {
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = prop.getProperty("updateComment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getCommentsTitle());
			pstmt.setInt(2, comment.getCommentsNo());
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return res;
	}
	
	public int deleteComment(Connection conn, int commentsNo) {
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = prop.getProperty("deleteComment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentsNo);
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return res;
	}
	
	public int deleteGroupComment(Connection conn, int commentsNo) {
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = prop.getProperty("deleteGroupComment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentsNo);
			pstmt.setInt(2, commentsNo);
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return res;
	}
	
	public int deleteAllComment(Connection conn, int reviewNo) {
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = prop.getProperty("deleteAllComment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return res;
	}

	public int getCommentNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getCommentNo");
		int no = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				no = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return no;
	}

	public Comment selectComment(Connection conn, int commentsNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectComment");
		Comment comment = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentsNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				comment = new Comment();

				comment.setCommentsDate(rset.getString("COMMENTS_DATE"));
				comment.setCommentsDeleteYn(rset.getString("COMMENTS_DELETE_YN"));
				comment.setCommentsNo(rset.getInt("COMMENTS_NO"));
				comment.setCommentsTitle(rset.getString("COMMENTS_CONTENT"));
				comment.setCommentsType(rset.getString("COMMENTS_TYPE"));
				comment.setpCommentsNo(rset.getInt("P_COMMENTS_NO"));
				comment.setId(rset.getString("ID"));
				comment.setName(rset.getString("NAME"));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return comment;
	}
	
}
