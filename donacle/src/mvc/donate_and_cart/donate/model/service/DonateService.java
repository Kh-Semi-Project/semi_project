package mvc.donate_and_cart.donate.model.service;

import static mvc.donate_and_cart.common.JdbcTemplate.getConnection;
import static mvc.donate_and_cart.common.JdbcTemplate.rollback;
import static mvc.donate_and_cart.common.JdbcTemplate.close;
import static mvc.donate_and_cart.common.JdbcTemplate.commit;

import java.sql.Connection;
import java.util.List;

import mvc.donate_and_cart.donate.model.dao.DonateDao;
import mvc.donate_and_cart.donate.model.vo.Donate;

public class DonateService {

	private DonateDao donateDao = new DonateDao();
	

	public List<Donate> selectDonateUser(String id) {
		Connection conn = getConnection();
		List<Donate> list = donateDao.selectDonateUser(conn, id);
		close(conn);
		return list;
	}
	
	public int insertDonate(String id) {
		Connection conn = getConnection();
		int result = 0;
		result = donateDao.insertDonate(conn, id);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	public int selectDonateCount(String id) {
		Connection conn = getConnection();
		int d_count = donateDao.selectDonateCount(conn, id);
		close(conn);
		return d_count;
	}

	public List<Donate> selectDonateInfo(String id) {
		Connection conn = getConnection();
		List<Donate> list = donateDao.selectDonateInfo(conn, id);
		close(conn);
		return list;
	}

}
