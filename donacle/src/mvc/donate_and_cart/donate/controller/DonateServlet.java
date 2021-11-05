package mvc.donate_and_cart.donate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.donate_and_cart.donate.model.service.DonateService;
import mvc.donate_and_cart.donate.model.vo.Donate;
import mvc.login_join_and_management.model.vo.Member;

/**
 * Servlet implementation class DonateServlet
 */
@WebServlet("/donate")
public class DonateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DonateService donateService = new DonateService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력 값 처리
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		if(member == null) {
			String msg = "로그인 후 이용 가능합니다.";
			request.setAttribute("msg", msg);
		} else {
			String id = member.getId();
			// 2. 업무 로직
			List<Donate> list = donateService.selectDonateUser(id);
			
			int donate_count = donateService.selectDonateCount(id);
			System.out.println("donate@"+donate_count);
			request.setAttribute("donate_user_info", list);
			request.setAttribute("donate_count", donate_count);
		}
		// 3. view단 처리
		request.setAttribute("loginMember", member);
		request	
			.getRequestDispatcher("/WEB-INF/views/donate_and_cart/donate_index.jsp")
			.forward(request, response);
	}

}
