package mvc.donate_and_cart.donate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.donate_and_cart.donate.model.vo.Donate;

/**
 * Servlet implementation class BuyWindowServlet
 */
@WebServlet("/donate/buy")
public class BuyWindowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 3. view단 처리
		request
			.getRequestDispatcher("/WEB-INF/views/donate_and_cart/BuyWindow.jsp")
			.forward(request, response);
	}

}
