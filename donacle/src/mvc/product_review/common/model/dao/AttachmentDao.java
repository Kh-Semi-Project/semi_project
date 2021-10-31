package mvc.product_review.common.model.dao;

import static mvc.sale_product.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import mvc.product_review.common.vo.Attach;
import mvc.product_review.review.model.exception.ReviewException;


public class AttachmentDao {
	
	private Properties prop = new Properties(); // Properties: board-query.properties의 query문 가져다 쓰는것 

	/**
	 * prop객체에 buildpath로 배포된 /sql/board/board-query.properties 불러오기
	 */
	public AttachmentDao() {
		String filepath = AttachmentDao.class.getResource("/sql/product_review/product_review-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public int deleteAttachment(Connection conn, int reviewNo) {
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = prop.getProperty("deleteAttachFile");

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

	public int updateAttach(Connection conn, Attach attach) {
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = prop.getProperty("updateAttachFile");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attach.getOriginalFilename());
			pstmt.setString(2, attach.getRenamedFilename());
			pstmt.setInt(3, attach.getAttachNo());
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return res;
	}

	public Attach selectAttach(Connection conn, int attachNo) {
		Attach attach = new Attach();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAttachFile");
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, attachNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()){

				attach.setAttachNo(attachNo);
				attach.setOriginalFilename(rset.getString("FILE_NAME"));
				attach.setRenamedFilename(rset.getString("UPDATED_FILE_NAME"));
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return attach;
	}

	public int insertAttachment(Connection conn, Attach attach) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachFile");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getReviewNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			
			result = pstmt.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
			throw new ReviewException("파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}