package mvc.donate_and_cart.cart.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static mvc.donate_and_cart.common.JdbcTemplate.close;

import mvc.donate_and_cart.cart.model.vo.CartList;

public class CartListDao {
	
	private Properties prop = new Properties();

	public CartListDao() {
		String filepath = CartListDao.class.getResource("/sql/donate_and_cart/cartList-query.properties").getPath();

		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<CartList> selectCartList(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCartList");
		List<CartList> list = new ArrayList<>();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				CartList cartList = new CartList();
				cartList.setCart_no(rset.getInt("cart_no"));
				cartList.setCartList_no(rset.getInt("list_no"));
				cartList.setId(rset.getString("id"));
				cartList.setProduct_cart_count(rset.getInt("cart_count"));
				cartList.setProduct_img(rset.getString("product_img"));
				cartList.setProduct_code(rset.getInt("product_code"));
				cartList.setProduct_name(rset.getString("product_name"));
				cartList.setProduct_price(rset.getInt("product_price"));
				cartList.setShipping_fee(rset.getInt("shipping_fee"));
				cartList.setProduct_writing_code(rset.getInt("product_writing_code"));
		
				System.out.println(cartList);
				list.add(cartList);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int deleteCart(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteCart"); 
		
		try {
			//????????????????????? ????????? ????????????.
			pstmt = conn.prepareStatement(query);
			//??????????????????
			pstmt.setInt(1, no);
			
			//??????????????? : ????????? ????????? ????????? ?????? pstmt??????(???????????? ??????)
			//DML??? executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		
		return result;
	}

	

}
