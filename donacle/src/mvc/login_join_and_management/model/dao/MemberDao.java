package mvc.login_join_and_management.model.dao;

import static mvc.login_join_and_management.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mvc.login_join_and_management.model.vo.Address;
import mvc.login_join_and_management.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();

	public MemberDao() {
		String filepath = MemberDao.class.getResource("/sql/login_join_management/login-join-management-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Member selectOneMember(String id, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = new Member();
		String sql = prop.getProperty("selectOneMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				member.setId(id);
				member.setPassword(rset.getString("password"));
				member.setName(rset.getString("name"));
				member.setEmail(rset.getString("email"));
				member.setPhone(rset.getString("phone"));
				member.setGender(rset.getString("gender"));
				member.setBirthday(rset.getDate("birthday"));
				member.setKind(rset.getString("kind"));
				member.setJoinDate(rset.getDate("join_date"));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	public Address selectAddressById(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAddressById");
		Address addr = new Address();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				addr.setId(id);
				addr.setZipCode(rset.getString("zip_code"));
				addr.setAddress(rset.getString("address"));
				addr.setDetailAddress(rset.getString("detail_address"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return addr;
	}
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getGender());
			pstmt.setDate(7, member.getBirthday());
			pstmt.setString(8, member.getKind());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertAddress(Connection conn, Address addr) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAddress");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, addr.getId());
			pstmt.setString(2, addr.getZipCode());
			pstmt.setString(3, addr.getAddress());
			pstmt.setString(4, addr.getDetailAddress());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getGender());
			pstmt.setDate(5, member.getBirthday());
			pstmt.setString(6, member.getId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateAddress(Connection conn, Address addr) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateAddress");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, addr.getZipCode());
			pstmt.setString(2, addr.getAddress());
			pstmt.setString(3, addr.getDetailAddress());
			pstmt.setString(4, addr.getId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteMember(Connection conn, String id) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public String findIdByNameAndEmail(Connection conn, String name, String email) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String result = "";
		String sql = prop.getProperty("findIdByNameAndEmail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public Member selectMemberByIdAndEmail(Connection conn, String id, String email) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = new Member();
		String sql = prop.getProperty("selectMemberByIdAndEmail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				member.setId(rset.getString("id"));
				member.setPassword(rset.getString("password"));	
				member.setEmail(rset.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

	public int updatePassword(Connection conn, String id, String password) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updatePassword");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, password);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int countTotalMembers(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = prop.getProperty("countTotalMembers");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public List<Member> selectAllMembers(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> list = new ArrayList<>();
		String sql = prop.getProperty("selectAllMembers");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member member = new Member();
				member.setId(rset.getString("id"));
				member.setPassword(null);
				member.setName(rset.getString("name"));
				member.setEmail(rset.getString("email"));
				member.setPhone(rset.getString("phone"));
				member.setBirthday(rset.getDate("birthday"));
				member.setGender(rset.getString("gender"));
				member.setKind(rset.getString("kind"));
				member.setJoinDate(rset.getDate("join_date"));
				
				if(rset.getString("address_id") != null) {
					Address addr = new Address();
					addr.setId(rset.getString("address_id"));
					addr.setZipCode(rset.getString("zip_code"));
					addr.setAddress(rset.getString("address"));
					addr.setDetailAddress(rset.getString("detail_address"));
					
					member.setAddress(addr);
				}
				
				list.add(member);
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

	public int countMemberById(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = prop.getProperty("countMemberById");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int hasBuyList(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = prop.getProperty("hasBuyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int hasBuyListBySeller(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = prop.getProperty("hasBuyListBySeller");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
	
}
