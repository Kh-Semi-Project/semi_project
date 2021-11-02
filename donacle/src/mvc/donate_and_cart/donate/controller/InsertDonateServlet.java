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
 * Servlet implementation class InsertDonateServlet
 */
@WebServlet("/donate/insert")
public class InsertDonateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DonateService donateService = new DonateService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력 값 처리
//		String id = request.getParameter("id");
		String id = "test0";
		// 2. 업무 로직
		int result = donateService.insertDonate(id);

		// 3. view단 처리
		String location = request.getContextPath() + "/donate?id=" + id; 
		response.sendRedirect(location);
	}

}
