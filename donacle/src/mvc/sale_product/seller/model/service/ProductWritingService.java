package mvc.sale_product.seller.model.service;

import static mvc.sale_product.common.JdbcTemplate.close;
import static mvc.sale_product.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import mvc.sale_product.seller.model.dao.ProductWritingDao;
import mvc.sale_product.seller.model.vo.ProductWriting;

public class ProductWritingService {
	ProductWritingDao pwdao = new ProductWritingDao();
	
	public List<ProductWriting> selectProductWritingList() {
		Connection conn = getConnection();
		List<ProductWriting> list = pwdao.selectProductWritingList(conn);
		close(conn);
		return list;
	}

	public int selectTotalContents() {
		Connection conn = getConnection();
		int totalContents = pwdao.selectTotalContents(conn);
		close(conn);
		return totalContents;
	}

}
