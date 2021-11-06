package mvc.login_join_and_management.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.login_join_and_management.model.service.MemberService;
import mvc.login_join_and_management.model.vo.Member;

/**
 * Servlet implementation class AdminMemberDeleteServlet
 */
@WebServlet("/admin/memberDelete")
public class AdminMemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println("id@" + id);
		Member member = memberService.selectOneMember(id);
		System.out.println("member@" + member);
		HttpSession session = request.getSession();
		String msg = "";

		if (MemberService.MEMBER_KIND_CUSTOMER.equals(member.getKind())) {
			int hasBuyList = memberService.hasBuyList(id);
			System.out.println("hasBuyList@" + hasBuyList);
			if (hasBuyList > 0) {
				msg = "아직 수령이 완료되지 않은 구매 물품이 있습니다. 구매 물품을 확인 바랍니다.";
				session.setAttribute("msg", msg);
				response.sendRedirect(request.getContextPath() + "/member/myPage");
			} else {
				int result = memberService.deleteMember(id);

				if (result > 0) {
					msg = "성공적으로 회원 정보를 삭제했습니다.";
				} else {
					msg = "회원 정보 삭제에 실패했습니다. 점검 후 다시 시도하십시오.";
				}
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(request.getContextPath() + "/admin/memberList");
			}
		}

		else if (MemberService.MEMBER_KIND_SELLER.equals(member.getKind())) {
			int hasBuyListBySeller = memberService.hasBuyListBySeller(id);
			System.out.println("hasBuyList@" + hasBuyListBySeller);
			if (hasBuyListBySeller > 0) {
				msg = "아직 수령이 완료되지 않은 판매 물품이 있습니다. 판매 물품을 확인 바랍니다.";
				session.setAttribute("msg", msg);
				response.sendRedirect(request.getContextPath() + "/member/myPage");
			} else {
				int result = memberService.deleteMember(id);

				if (result > 0) {
					msg = "성공적으로 회원 정보를 삭제했습니다.";
				} else {
					msg = "회원 정보 삭제에 실패했습니다. 점검 후 다시 시도하십시오.";
				}
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(request.getContextPath() + "/admin/memberList");
			}

		} else {

			int result = memberService.deleteMember(id);

			if (result > 0) {
				msg = "성공적으로 회원 정보를 삭제했습니다.";
			} else {
				msg = "회원 정보 삭제에 실패했습니다. 점검 후 다시 시도하십시오.";
			}
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/admin/memberList");
		}
	}

}
