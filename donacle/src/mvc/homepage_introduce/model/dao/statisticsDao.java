package mvc.homepage_introduce.model.dao;

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

import mvc.homepage_introduce.model.vo.Statistics;

public class statisticsDao {

	private Properties prop = new Properties();

	public statisticsDao() {
		String filepath = statisticsDao.class.getResource("/sql/homepage_introduce/statistics-query.properties").getPath();
		System.out.println(filepath);
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Statistics> selectStatistics(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectStatistics");
		List<Statistics> list = new ArrayList<>();
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					// 테이블 record 1 -> VO객체 1
					Statistics st = new Statistics();
					st.setDe_no(rset.getInt("de_no"));
					st.setDe_type(rset.getString("de_type"));
					st.setCategory(rset.getString("category"));
					st.setDe_rate(rset.getDouble("de_rate"));
					st.setPeriod(rset.getInt("period"));
					
					list.add(st);
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

