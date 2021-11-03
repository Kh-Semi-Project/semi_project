package mvc.login_join_and_management.controller.management;

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
 * Servlet implementation class findPasswordServlet
 */
@WebServlet("/findPassword")
public class FindPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/views/login_join_and_management/management/findPasswordForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		String email = request.getParameter("findPassEmail");
		
		Member member = memberService.selectMemberByIdAndEmail(id, email);
		
		HttpSession session = request.getSession();
		if(id.equals(member.getId()) && email.equals(member.getEmail())) {
			session.setAttribute("findMember", member);
			response.sendRedirect(request.getContextPath() + "/updatePassword");
		} else {
			String msg = "회원정보가 없습니다. 확인 후 다시 시도해주세요.";
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/findPassword");
		}
	}
}
