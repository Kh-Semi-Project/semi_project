package mvc.login_join_and_management.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.login_join_and_management.common.MvcUtils;
import mvc.login_join_and_management.model.service.MemberService;
import mvc.login_join_and_management.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/memberLogin")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login_join_and_management//login/loginForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = MvcUtils.getEncryptedPassword(request.getParameter("password"));
//		System.out.println("id/pass@loginServlet = " + id + ", " + password);
		
		Member member = memberService.selectOneMember(id);
//		System.out.println("member@loginServlet = " + member);
		
		String msg = "";
		HttpSession session = request.getSession();
		
		if(password.equals(member.getPassword())) {
			session.setAttribute("loginMember", member);
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			msg = "로그인 실패! 아이디 또는 비밀번호를 확인하세요.";
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/memberLogin");
		}
	}

}
