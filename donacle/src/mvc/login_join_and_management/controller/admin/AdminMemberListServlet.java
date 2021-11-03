package mvc.login_join_and_management.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.login_join_and_management.common.MvcUtils;
import mvc.login_join_and_management.model.service.MemberService;
import mvc.login_join_and_management.model.vo.Member;

/**
 * Servlet implementation class AdminMemberListServlet
 */
@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int cPage = 1;
		int numPerPage = 10;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			// 처리코드 없음.
		}
		int startRownum = cPage * numPerPage - (numPerPage - 1);
		int endRownum = cPage * numPerPage;
		// 2. 업무 로직
		// a. content 영역
		List<Member> list = memberService.selectAllMembers(startRownum, endRownum);
//				System.out.println("list@BoardListServlet = " + list);

		// b. pagebar영역
		int totalMembers = memberService.countTotalMembers(); // 총 회원수
//				System.out.println("totalContentsServlet = " + totalContents);
		String url = request.getRequestURI();
		String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalMembers, url);
//				System.out.println("pagebar@AdminMemberListServlet = " + pagebar);

		// 3. view 처리
		request.setAttribute("list", list);
		request.setAttribute("pagebar", pagebar);
		request.getRequestDispatcher("/WEB-INF/views/login_join_and_management/admin/memberList.jsp").forward(request, response);
	}

}
