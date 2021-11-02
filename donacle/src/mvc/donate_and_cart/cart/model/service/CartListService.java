package mvc.donate_and_cart.cart.model.service;

import static mvc.donate_and_cart.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import mvc.donate_and_cart.cart.model.dao.CartListDao;
import mvc.donate_and_cart.cart.model.vo.CartList;

public class CartListService {
	
	private CartListDao cartListDao = new CartListDao();

	public List<CartList> selectCartList(String id) {
		Connection conn = getConnection();
		List<CartList> list = cartListDao.selectCartList(conn, id);
		return list;
	}

	public int deleteCart(int no) {
		Connection conn = getConnection();
		int result = 0;
		result = cartListDao.deleteCart(conn, no);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	

}
