package mvc.login_join_and_management.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.login_join_and_management.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberDeleteServlet
 */
@WebServlet("/admin/memberDelete")
public class AdminMemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println(id);
		
		int result = memberService.deleteMember(id);
		
		String msg = ""; 
		if(result > 0) {
			msg = "성공적으로 회원 정보를 삭제했습니다.";
		} else {
			msg = "회원 정보 삭제에 실패했습니다. 점검 후 다시 시도하십시오.";
		}
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/admin/memberList");
	}

}
