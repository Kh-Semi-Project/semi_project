package mvc.donate_and_cart.donate.model.dao;

import static mvc.donate_and_cart.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mvc.donate_and_cart.cart.model.dao.CartListDao;
import mvc.donate_and_cart.cart.model.vo.CartList;
import mvc.donate_and_cart.donate.model.vo.Donate;

public class DonateDao {
	
	private Properties prop = new Properties();

	public DonateDao() {
<<<<<<< HEAD
		String filepath = DonateDao.class.getResource("/sql/donate_and_cart/donate-query.properties").getPath();
=======
		String filepath = DonateDao.class.getResource("/sql/donate/donate-query.properties").getPath();
>>>>>>> branch 'jkyeom' of https://github.com/Kh-Semi-Project/semi_project.git
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Donate> selectDonateUser(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectclickdonate");
		List<Donate> list = new ArrayList<>();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Donate donate = new Donate();
				donate.setName(rset.getString("name"));
				donate.setDonate_price(rset.getInt("donate_price"));
		
				list.add(donate);
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

	public int insertDonate(Connection conn, String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertDonate"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, id);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		
		return result;
	}

	public int selectDonateCount(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectDonateCount");
		int d_count = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				d_count = rset.getInt("count");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return d_count;
	}

	public List<Donate> selectDonateInfo(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectDonateInfo");
		List<Donate> list = new ArrayList<>();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Donate donate = new Donate();
				donate.setName(rset.getString("name"));
				donate.setDonate_time(rset.getDate("donate_time"));
				donate.setHow_donate(rset.getString("how_donate"));
				donate.setDonate_price(rset.getInt("donate_price"));
		
				list.add(donate);
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
	

}
