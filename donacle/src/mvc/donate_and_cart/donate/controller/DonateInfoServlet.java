package mvc.donate_and_cart.donate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.donate_and_cart.donate.model.service.DonateService;
import mvc.donate_and_cart.donate.model.vo.Donate;

/**
 * Servlet implementation class DonateInfoServlet
 */
@WebServlet("/donate/info")
public class DonateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DonateService donateService = new DonateService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력 값 처리
//		String id = request.getParameter("id");
		String id = "test0";
		// 2. 업무 로직
		List<Donate> list = donateService.selectDonateInfo(id);
		
		request.setAttribute("donate_info", list);

		// 3. view단 처리
		request
			.getRequestDispatcher("/WEB-INF/views/donate_and_cart/myDonateInfo.jsp")
			.forward(request, response);
	}

}
