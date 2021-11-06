package mvc.login_join_and_management.controller.admin;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.login_join_and_management.model.service.MemberService;
import mvc.login_join_and_management.model.vo.Address;
import mvc.login_join_and_management.model.vo.Member;



/**
 * Servlet implementation class AdminMemberUpdateServlet
 */
@WebServlet("/admin/memberUpdate")
public class AdminMemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		Member member = memberService.selectOneMember(id);

		if (member != null) {
			request.setAttribute("memberById", member);
			request.getRequestDispatcher("/WEB-INF/views/login_join_and_management/admin/adminUpdateForm.jsp")
					.forward(request, response);
		} else {
			String msg = "회원 정보 조회에 실패했습니다.";
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/admin/memberList");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String _birthday = request.getParameter("birthday");
		String kind = request.getParameter("kind");
		
		if("구매자".equals(kind)) {
			kind = MemberService.MEMBER_KIND_CUSTOMER;
		} else if ("판매자".equals(kind)) {
			kind = MemberService.MEMBER_KIND_SELLER;
		} else {
			kind = MemberService.MEMBER_KIND_ADMIN;
		}
		
		Date birthday = null;
		if(_birthday != null && !"".equals(_birthday)) {
			SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyyMMdd");
			java.util.Date beforeDate = null;
			
			try {
				beforeDate = beforeFormat.parse(_birthday);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-MM-dd");
			String format = afterFormat.format(beforeDate);
			birthday = Date.valueOf(format);
		}
		
		Member member = new Member(id, null, name, email, phone, gender, birthday, kind, null, null);
		
		if(MemberService.MEMBER_KIND_CUSTOMER.equals(kind)) {
			
			String zipCode = request.getParameter("zipCode");
			String address = request.getParameter("address");
			String detailAddress = request.getParameter("detailAddress");
			
			Address addr = new Address(id, zipCode, address, detailAddress);
			
			member.setAddress(addr);
		}
		
		System.out.println(member);
		
		
		int result = memberService.updateMember(member);
		
		String msg = "";
		HttpSession session = request.getSession();
		if(result > 0) {
			msg = "성공적으로 회원님의 정보를 수정했습니다.";
			Member newMember = memberService.selectOneMember(id);
			System.out.println(newMember);
			
			session.setAttribute("memberById", newMember);
			session.setAttribute("msg", msg);
			
			response.sendRedirect(request.getContextPath() + "/admin/memberDetail?id=" + id);
		} else {
			msg = "회원 정보 수정에 실패했습니다.";
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/admin/memberUpdate");
		}
		
		
		
	}
}
