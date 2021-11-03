package mvc.login_join_and_management.controller.join;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.login_join_and_management.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		int result = memberService.deleteMember(id);
		
		HttpSession session = request.getSession();
		String msg = "";
		if(result > 0) {
			msg = "회원 탈퇴를 완료했습니다. 그 동안 Donacle을 이용해 주셔서 감사합니다. :)";
			session.removeAttribute("loginMember");
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			msg = "회원 가입에 실패했습니다. 지속적인 오류 발생 시 관리자에게 문의 바랍니다.";
			session.setAttribute("msg", msg);
			request.getRequestDispatcher(request.getContextPath() + "/member/memberUpdate");
		}
	}

}
