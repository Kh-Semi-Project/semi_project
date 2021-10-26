package mvc.sale_product.seller.model.dao;

import static mvc.sale_product.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import mvc.sale_product.seller.model.vo.ProductWriting;

public class ProductWritingDao {
	private Properties prop = new Properties();

	public ProductWritingDao() {
		String filepath = ProductWritingDao.class.getResource("/sql/sale_product/productwriting-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int selectTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalContents");
		int totalContents = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContents = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}
	public List<ProductWriting> selectProductWritingList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProductWritingList");
		List<ProductWriting> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// 테이블 record 1 -> VO객체 1
				ProductWriting pw = new ProductWriting();
				pw.setId(rset.getString("id"));
				pw.setProduct_code(rset.getInt("product_writing_code"));
				pw.setRead_count(rset.getInt("read_count"));
				pw.setProduct_writing_date(rset.getDate("product_writing_date"));
				pw.setCategory_code(rset.getInt("category_code"));
				pw.setProduct_price(rset.getInt("product_price"));
				pw.setProduct_img(rset.getString("product_img"));
				pw.setProduct_count(rset.getInt("product_count")); //남은 제품 개수
				pw.setCategory_name(rset.getString("category_name"));
				list.add(pw);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

}
