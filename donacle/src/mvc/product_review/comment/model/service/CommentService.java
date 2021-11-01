package mvc.product_review.comment.model.service;

import static mvc.product_review.common.JdbcTemplate.close;
import static mvc.product_review.common.JdbcTemplate.commit;
import static mvc.product_review.common.JdbcTemplate.getConnection;
import static mvc.product_review.common.JdbcTemplate.rollback;

import java.sql.Connection;

import mvc.product_review.comment.model.dao.CommentDao;
import mvc.product_review.comment.model.vo.Comment;

public class CommentService {
	private CommentDao commentDao = new CommentDao();

	public int deleteComments(int commentsNo, int commentsType) {
		Connection conn = getConnection();
		int num = 0;

		try {
			if (commentsType == 1) {
				num = commentDao.deleteGroupComment(conn, commentsNo);
			} else if (commentsType == 2) {
				num = commentDao.deleteComment(conn, commentsNo);
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}

		return num;
	}

	public int insertComment(Comment comment) {
		Connection conn = getConnection();
		int num = 0;

		try {
			num = commentDao.insertComment(conn, comment);
			commit(conn);
			if (num > 0)
				num = commentDao.getCommentNo(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return num;
	}

	public int updateComment(Comment comment) {
		Connection conn = getConnection();
		int num = 0;

		try {
			num = commentDao.updateComment(conn, comment);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return num;
	}

	public Comment selectComment(int commentsNo) {
		Connection conn = getConnection();
		Comment comment = null;

		try {
			comment = commentDao.selectComment(conn, commentsNo);

		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return comment;
	}
}
