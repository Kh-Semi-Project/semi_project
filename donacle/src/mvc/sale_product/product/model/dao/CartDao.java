package mvc.sale_product.product.model.dao;

import static mvc.sale_product.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class CartDao {
	private Properties prop = new Properties();

	//properties에 한번은 연결해야 연결 가능
	public CartDao() {
		String filepath = ProductDao.class.getResource("/sql/sale_product/cart-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	//장바구니에 추가
	public int insertCart(Connection conn, int cartNo,int product_code, int product_buy_count) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCart");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartNo);
			pstmt.setInt(2, product_code);
			pstmt.setInt(3, product_buy_count);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int selectCartNo(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCartNo");
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}
	
}
