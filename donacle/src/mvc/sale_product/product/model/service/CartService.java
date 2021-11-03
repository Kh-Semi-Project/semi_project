package mvc.sale_product.product.model.service;

import static mvc.sale_product.common.JdbcTemplate.close;
import static mvc.sale_product.common.JdbcTemplate.getConnection;
import static mvc.sale_product.common.JdbcTemplate.commit;
import static mvc.sale_product.common.JdbcTemplate.rollback;

import java.sql.Connection;

import mvc.sale_product.product.model.dao.CartDao;

public class CartService {
	CartDao cd = new CartDao();
	public int insertCart(int cartNo, int product_code,int product_buy_count) {
		Connection conn = getConnection();
		int result = cd.insertCart(conn,cartNo,product_code,product_buy_count);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int selectCartNo(String id) {
		Connection conn = getConnection();
		int totalContents = cd.selectCartNo(conn,id);
		close(conn);
		return totalContents;
	}
}
