package mvc.homepage_introduce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.homepage_introduce.model.service.statisticsService;
import mvc.homepage_introduce.model.vo.Statistics;
import mvc.sale_product.seller.model.vo.ProductWriting;

/**
 * Servlet implementation class statisticsServlet
 */
@WebServlet("/homepage_introduce/introduce")
public class statisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private statisticsService sts = new statisticsService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Statistics> list = sts.selectStatistics();
		System.out.println("list@servlet = " + list);
		
		request.setAttribute("introduce", list);
		request
			.getRequestDispatcher("/WEB-INF/views/homepage_introduce/homepage_introduce.jsp")
			.forward(request, response);
		}

}
