package mvc.login_join_and_management.controller.management;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.login_join_and_management.common.MvcUtils;
import mvc.login_join_and_management.model.service.MemberService;

/**
 * Servlet implementation class UpdatePasswordServlet
 */
@WebServlet("/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login_join_and_management/management/updatePasswordForm.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String newPass = MvcUtils.getEncryptedPassword(request.getParameter("newPass"));
		
		int result = memberService.updatePassword(id, newPass);
		
		
		
		String msg = "";
		
		if (result > 0) {
			msg = "비밀번호를 성공적으로 변경했습니다. 새 비밀번호로 로그인하세요.";
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/memberLogin");
		} else {
			msg = "비밀번호 변경에 실패했습니다. 다시 시도해주세요.";
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/updatePassword");
		}
	}

}
