package mvc.login_join_and_management.model.service;

import static mvc.login_join_and_management.common.JdbcTemplate.close;
import static mvc.login_join_and_management.common.JdbcTemplate.commit;
import static mvc.login_join_and_management.common.JdbcTemplate.getConnection;
import static mvc.login_join_and_management.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import mvc.login_join_and_management.model.dao.MemberDao;
import mvc.login_join_and_management.model.vo.Address;
import mvc.login_join_and_management.model.vo.Member;

public class MemberService {
	public final static String MEMBER_GENDER_MALE = "M";
	public final static String MEMBER_GENDER_FEMALE = "F";
	
	public final static String MEMBER_KIND_ADMIN = "A";
	public final static String MEMBER_KIND_SELLER = "S";
	public final static String MEMBER_KIND_CUSTOMER = "C";
	
	private MemberDao memberDao = new MemberDao();

	public Member selectOneMember(String id) {
		Connection conn = getConnection();
		Member member = memberDao.selectOneMember(id, conn);
		
		if(MemberService.MEMBER_KIND_CUSTOMER.equals(member.getKind())) {
			Address addr = new Address();
			addr = memberDao.selectAddressById(conn, id);
			
			member.setAddress(addr);
		}
		close(conn);
		return member;
	}

	public int insertMember(Member member) {
		Connection conn = getConnection();
		Address addr = member.getAddress();
		int result = 0;
		
		result = memberDao.insertMember(conn, member);
		
		if(addr != null) {
			result = memberDao.insertAddress(conn, addr);
		}
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateMember(Member member) {
		Connection conn = getConnection();
		Address addr = member.getAddress();
		int result = 0;
		
		result = memberDao.updateMember(conn, member);
		
		if(addr != null) {
			result = memberDao.updateAddress(conn, addr);
		}
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deleteMember(String id) {
		Connection conn = getConnection();
		int result = 0;
		
		result = memberDao.deleteMember(conn, id);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public String findIdByNameAndEmail(String name, String email) {
		Connection conn = getConnection();
		String result = memberDao.findIdByNameAndEmail(conn, name, email);
		close(conn);
		return result;
	}

	public Member selectMemberByIdAndEmail(String id, String email) {
		Connection conn = getConnection();
		Member member = memberDao.selectMemberByIdAndEmail(conn, id, email);
		close(conn);
		return member;
	}

	public int updatePassword(String id, String password) {
		Connection conn = getConnection();
		int result = memberDao.updatePassword(conn, id, password);
		
		if (result > 0) 
			commit(conn); 
		else 
			rollback(conn); 
		
		close(conn);
		return result;
	}

	public int countTotalMembers() {
		Connection conn = getConnection();
		int result = memberDao.countTotalMembers(conn);
		
		close(conn);
		return result;
	}

	public List<Member> selectAllMembers(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<Member> list = memberDao.selectAllMembers(conn, startRownum, endRownum);
		close(conn);
		return list;
	}

	public int countMemberById(String id) {
		Connection conn = getConnection();
		int result = memberDao.countMemberById(conn, id);
		close(conn);
		return result;
	}

	public int updateAddress(Address addr) {
		Connection conn = getConnection();
		int result = memberDao.updateAddress(conn, addr);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
