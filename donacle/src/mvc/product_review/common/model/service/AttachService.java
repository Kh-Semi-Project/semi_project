package mvc.product_review.common.model.service;

import static mvc.product_review.common.JdbcTemplate.commit;
import static mvc.product_review.common.JdbcTemplate.close;
import static mvc.product_review.common.JdbcTemplate.getConnection;
import static mvc.product_review.common.JdbcTemplate.rollback;

import java.sql.Connection;

import mvc.product_review.common.model.dao.AttachmentDao;
import mvc.product_review.common.vo.Attach;

public class AttachService {
	
	private AttachmentDao attachmentDao = new AttachmentDao();
	
	public Attach selectAttach(int attachNo) {
		Connection conn = getConnection();
		Attach attach = null;
		
		try {
			attach = attachmentDao.selectAttach(conn,attachNo);
			
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		
		return attach;
	}

	public int deleteAttach(int reviewNo) {
		Connection conn = getConnection();
		int num = 0;
		
		try {
			num = attachmentDao.deleteAttachment(conn, reviewNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		
		return num;
	}

}
