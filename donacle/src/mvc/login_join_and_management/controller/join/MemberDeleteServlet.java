package mvc.login_join_and_management.controller.join;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.login_join_and_management.model.service.MemberService;
import mvc.login_join_and_management.model.vo.Member;
import mvc.product_review.common.MvcUtils;
import mvc.sale_product.product.model.vo.ProductBuy;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
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
					msg = "회원 탈퇴를 완료했습니다. 그 동안 Donacle을 이용해 주셔서 감사합니다. :)";
					session.removeAttribute("loginMember");
					session.setAttribute("msg", msg);
					response.sendRedirect(request.getContextPath() + "/");
				} else {
					msg = "회원 탈퇴에 실패했습니다. 지속적인 오류 발생 시 관리자에게 문의 바랍니다.";
					session.setAttribute("msg", msg);
					request.getRequestDispatcher(request.getContextPath() + "/member/myPage");
				}

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
					msg = "회원 탈퇴를 완료했습니다. 그 동안 Donacle을 이용해 주셔서 감사합니다. :)";
					session.removeAttribute("loginMember");
					session.setAttribute("msg", msg);
					response.sendRedirect(request.getContextPath() + "/");
				} else {
					msg = "회원 탈퇴에 실패했습니다. 지속적인 오류 발생 시 관리자에게 문의 바랍니다.";
					session.setAttribute("msg", msg);
					request.getRequestDispatcher(request.getContextPath() + "/member/myPage");
				}
			}
		} else {
			
			int result = memberService.deleteMember(id);

			if (result > 0) {
				msg = "회원 탈퇴를 완료했습니다. 그 동안 Donacle을 이용해 주셔서 감사합니다. :)";
				session.removeAttribute("loginMember");
				session.setAttribute("msg", msg);
				response.sendRedirect(request.getContextPath() + "/");
			} else {
				msg = "회원 탈퇴에 실패했습니다. 지속적인 오류 발생 시 관리자에게 문의 바랍니다.";
				session.setAttribute("msg", msg);
				request.getRequestDispatcher(request.getContextPath() + "/member/myPage");
			}
		}
	}

}
