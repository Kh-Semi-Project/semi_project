package mvc.login_join_and_management.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.login_join_and_management.model.service.MemberService;
import mvc.login_join_and_management.model.vo.Member;

/**
 * Servlet implementation class AdminMemberDetailServlet
 */
@WebServlet("/admin/memberDetail")
public class AdminMemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		Member member = memberService.selectOneMember(id);
		
		if(member != null) {
			request.setAttribute("memberById", member);
			request.getRequestDispatcher("/WEB-INF/views/login_join_and_management/admin/adminDetailForm.jsp").forward(request, response);	
		} else {
			String msg = "회원 정보 조회에 실패했습니다.";
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/admin/memberList");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
