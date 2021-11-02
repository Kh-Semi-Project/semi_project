package mvc.product_review.review.model.dao;

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

import mvc.product_review.common.vo.Attach;
import mvc.product_review.review.model.exception.ReviewException;
import mvc.product_review.review.model.vo.BuyLog;
import mvc.product_review.review.model.vo.Review;


public class ReviewDao {
	
	private Properties prop = new Properties(); // Properties: board-query.properties의 query문 가져다 쓰는것 

	/**
	 * prop객체에 buildpath로 배포된 /sql/board/board-query.properties 불러오기
	 */
	public ReviewDao() {
		String filepath = ReviewDao.class.getResource("/sql/product_review/product_review-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertReview(Connection conn, Review review) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReview");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review.getProductBuyCode());
			pstmt.setString(2, review.getId());
			pstmt.setString(3, review.getReviewTitle());
			pstmt.setString(4, review.getReviewContent());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ReviewException("후기 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Review> selectReivewList(Connection conn,int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReivewList");
		
		List<Review> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Review review = new Review();
				review.setReviewNo(rset.getInt("REVIEW_NO"));
				review.setId(rset.getString("ID"));
				review.setName(rset.getString("NAME"));
				review.setProductBuyCode(rset.getInt("PRODUCT_BUY_CODE"));
				review.setReviewContent(rset.getString("REVIEW_CONTENT"));	
				review.setReviewDate(rset.getDate("REVIEW_DATE"));
				review.setReviewDeleteYn(rset.getString("REVIEW_DELETE_YN"));
				review.setReviewTitle(rset.getString("REVIEW_TITLE"));
				
				Attach attach = new Attach();
				attach.setAttachNo(rset.getInt("ATTACHMENT_NO"));
				attach.setReviewNo(review.getReviewNo());
				attach.setOriginalFilename(rset.getString("FILE_NAME"));
				attach.setRenamedFilename(rset.getString("UPDATED_FILE_NAME"));
				
				review.setAttach(attach);
				list.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectReivewListCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReviewListCnt");
		int totalContents = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContents = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public int deleteReview(Connection conn, int reivewNo) {
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = prop.getProperty("deleteReview");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reivewNo);
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return res;
	}

	public int updatePurchaseWriting(Connection conn, int reviewNo, char c) {
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = prop.getProperty("updatePurchaseWriting");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c+"");
			pstmt.setInt(2, reviewNo);
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return res;
	}

	public int updateReview(Connection conn, Review review) {
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = prop.getProperty("updateReview");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getReviewTitle());
			pstmt.setString(2, review.getReviewContent());
			pstmt.setInt(3, review.getReviewNo());
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return res;
	}

	public List<BuyLog> selectBuyLog(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBuyLog");
		
		List<BuyLog> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BuyLog buyLog = new BuyLog();
				buyLog.setPurchaseBuyCode(rset.getInt("PRODUCT_BUY_CODE"));
		
				list.add(buyLog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getReivewNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getReviewNo");
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

	public Review selectReview(Connection conn, int reviewNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReview");
		
		Review review = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				review = new Review();
				review.setReviewNo(rset.getInt("REVIEW_NO"));
				review.setId(rset.getString("ID"));
				review.setName(rset.getString("NAME"));
				review.setProductBuyCode(rset.getInt("PRODUCT_BUY_CODE"));
				review.setReviewContent(rset.getString("REVIEW_CONTENT"));	
				review.setReviewDate(rset.getDate("REVIEW_DATE"));
				review.setReviewDeleteYn(rset.getString("REVIEW_DELETE_YN"));
				review.setReviewTitle(rset.getString("REVIEW_TITLE"));
				
				Attach attach = new Attach();
				attach.setAttachNo(rset.getInt("ATTACHMENT_NO"));
				attach.setReviewNo(review.getReviewNo());
				attach.setOriginalFilename(rset.getString("FILE_NAME"));
				attach.setRenamedFilename(rset.getString("UPDATED_FILE_NAME"));
				
				review.setAttach(attach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return review;
	}
}	
