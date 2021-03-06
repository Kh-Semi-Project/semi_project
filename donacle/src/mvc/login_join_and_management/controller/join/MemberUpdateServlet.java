package mvc.login_join_and_management.controller.join;

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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login_join_and_management/join/updateForm.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String _birthday = request.getParameter("birthday");
		String kind = request.getParameter("kind");
		
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
		
		
		int result = memberService.updateMember(member);
		
		String msg = "";
		HttpSession session = request.getSession();
		if(result > 0) {
			msg = "??????????????? ???????????? ????????? ??????????????????.";
			Member newMember = memberService.selectOneMember(id);
			
			session.setAttribute("loginMember", newMember);
			session.setAttribute("msg", msg);
			
			response.sendRedirect(request.getContextPath() + "/member/memberDetail");
		} else {
			msg = "?????? ?????? ????????? ??????????????????. ???????????? ?????? ?????? ??? ??????????????? ?????? ????????????.";
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/member/memberUpdate");
		}
		
		
		
	}

}
