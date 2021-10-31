package mvc.product_review.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.product_review.review.model.service.ReviewService;
import mvc.product_review.review.model.vo.BuyLog;


/**
 * Servlet implementation class BoardCommentEnrollServlet
 */
@WebServlet("/review/reviewForm")
public class ReviewFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReviewService reviewService = new ReviewService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String id = ((Member)request.getSession().getAttribute("loginMember")).getId()
		String id = "test";
		
		List<BuyLog> list = reviewService.selectBuyLog(id);
		if(list != null && list.size() > 0) {
			request.setAttribute("buyLogList", list);
			request.getRequestDispatcher("/WEB-INF/views/product_review/reviewForm.jsp").forward(request, response);			
		}else {
			request.getRequestDispatcher("/WEB-INF/views/product_review/message/noPurchase.jsp").forward(request, response);
		}	
	}
}	
