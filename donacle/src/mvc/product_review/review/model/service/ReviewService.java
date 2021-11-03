package mvc.product_review.review.model.service;

import static mvc.product_review.common.JdbcTemplate.close;
import static mvc.product_review.common.JdbcTemplate.commit;
import static mvc.product_review.common.JdbcTemplate.getConnection;
import static mvc.product_review.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import mvc.product_review.comment.model.dao.CommentDao;
import mvc.product_review.comment.model.vo.Comment;
import mvc.product_review.common.model.dao.AttachmentDao;
import mvc.product_review.common.vo.Attach;
import mvc.product_review.review.model.dao.ReviewDao;
import mvc.product_review.review.model.vo.BuyLog;
import mvc.product_review.review.model.vo.Review;


public class ReviewService {

	private ReviewDao reviewDao = new ReviewDao();
	
	private CommentDao commentDao = new CommentDao();
	
	private AttachmentDao attachmentDao = new AttachmentDao();
	/**
	 * board테이블 한행 추가
	 * attachment테이블 한행 추가
	 * 
	 * @param board
	 * @return
	 */
	public int insertReview(Review review) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			// review테이블 행추가
			result = reviewDao.insertReview(conn, review);
			System.out.println("Review@service = ");
			
			// 생성된 reivew_no 가져오기
			int reviewNo = reviewDao.getReivewNo(conn);
			
			result += reviewDao.updatePurchaseWriting(conn, review.getProductBuyCode(), 'y');
			// attachment테이블 행추가
			Attach attach = review.getAttach();
			
			if(attach != null) {
				attach.setReviewNo(reviewNo);
				result = attachmentDao.insertAttachment(conn, attach);
			}
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			result = 0;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<Review> selectReviewList(int start, int end) {
		Connection conn = getConnection();
		List<Review> list = null;
		
		try {
			list = reviewDao.selectReivewList(conn,start,end);
			
			for (Review review : list) {
				List<Comment> comments = commentDao.selectCommentList(conn,review.getReviewNo());
				review.setCommentList(comments);
				review.setReviewCommentCnt(comments.size());
			}
			
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		
		return list;
	}

	public int selectReviewListCnt() {
		Connection conn = getConnection();
		int num = 0;
		
		try {
			num = reviewDao.selectReivewListCnt(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return num;
	}

	
	public int deleteReview(int reivewNo) {
		Connection conn = getConnection();
		int num = 0;
		
		try {
			num += commentDao.deleteAllComment(conn, reivewNo);
			num += attachmentDao.deleteAttachment(conn,reivewNo);
			num += reviewDao.deleteReview(conn,reivewNo);
			num += reviewDao.updatePurchaseWriting(conn,reivewNo,'n');
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return num;
	}

	public int updateReview(Review review) {
		Connection conn = getConnection();
		int num = 0;
		
		try {
			num += reviewDao.updateReview(conn,review);
			if(review.getAttach() != null) {
				Review temp = reviewDao.selectReview(conn, review.getReviewNo());
				
				if(temp.getAttach() != null && temp.getAttach().getAttachNo() != 0) {
					num += attachmentDao.updateAttach(conn,review.getAttach());
				}else {
					num += attachmentDao.insertAttachment(conn, review.getAttach());
				}			
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return num;
	}

	public List<BuyLog> selectBuyLog(String id) {
		Connection conn = getConnection();
		List<BuyLog> list = null;
		
		try {
			list = reviewDao.selectBuyLog(conn,id);
			
		} catch (Exception e) {
			rollback(conn);
		}
		
		close(conn);
		return list;
	}

	public Review selectReview(int reviewNo) {
		Connection conn = getConnection();
		Review review = null;
		
		try {
			review = reviewDao.selectReview(conn,reviewNo);
			
		} catch (Exception e) {
			rollback(conn);
		}
		
		close(conn);
		return review;
	}
	
}