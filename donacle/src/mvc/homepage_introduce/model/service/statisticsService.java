package mvc.homepage_introduce.model.service;

import static mvc.sale_product.common.JdbcTemplate.close;
import static mvc.sale_product.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import mvc.homepage_introduce.model.dao.statisticsDao;
import mvc.homepage_introduce.model.vo.Statistics;

public class statisticsService {

	statisticsDao stdao = new statisticsDao();
	
	public List<Statistics> selectStatistics() {
		Connection conn = getConnection();
		List<Statistics> list = stdao.selectStatistics(conn);
		close(conn);
		return list;
	}

}
