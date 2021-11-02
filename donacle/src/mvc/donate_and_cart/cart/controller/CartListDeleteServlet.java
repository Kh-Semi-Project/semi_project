package mvc.donate_and_cart.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.donate_and_cart.cart.model.service.CartListService;

/**
 * Servlet implementation class CartListDeleteServlet
 */
@WebServlet("/CartList/delete")
public class CartListDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartListService cartListService = new CartListService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력 값 전달
		int No = Integer.parseInt(request.getParameter("cartListNo"));
		System.out.println("도착");
		System.out.println(No);
		// 2. 업무 로직
		int result = cartListService.deleteCart(No);
		System.out.println(result > 0 ? "삭제 성공!" : "삭제 실패!");
		// 3. view단 처리
		//나중에 수정필요
		String location = request.getContextPath() + "/CartList?id=" + "test0";
		response.sendRedirect(location);
	}

}
