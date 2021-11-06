package mvc.login_join_and_management.controller.join;

import java.io.IOException;

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
 * Servlet implementation class MemberAddressUpdateServlet
 */
@WebServlet("/member/addressUpdate")
public class MemberAddressUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login_join_and_management/join/addressUpdateForm.jsp").forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		String zipCode = request.getParameter("zipCode");
		String address = request.getParameter("address");
		String _detailAddress = request.getParameter("detailAddress");
		String subAddress = request.getParameter("subAddress");
		
		String detailAddress = _detailAddress + subAddress;
		
		Address addr = new Address(id, zipCode, address, detailAddress);
			
		int result = memberService.updateAddress(addr);
		
		String msg = "";
		HttpSession session = request.getSession();
		if(result > 0) {
			msg = "성공적으로 회원님의 배송지 정보를 수정했습니다.";
			Member newMember = memberService.selectOneMember(id);
			
			session.setAttribute("loginMember", newMember);
			session.setAttribute("msg", msg);
			
			response.sendRedirect(request.getContextPath() + "/member/memberAddress");
		} else {
			msg = "배송지 정보 수정에 실패했습니다. 지속적인 오류 발생 시 관리자에게 문의 바랍니다.";
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/member/addressUpdate");
		}
	
	}

}
