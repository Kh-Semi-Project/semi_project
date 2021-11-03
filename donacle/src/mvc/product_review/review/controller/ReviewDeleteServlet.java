package mvc.product_review.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.product_review.review.model.service.ReviewService;
import mvc.product_review.review.model.vo.Review;


/**
 * Servlet implementation class BoardCommentEnrollServlet
 */
@WebServlet("/review/reviewDelete")
public class ReviewDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReviewService reviewService = new ReviewService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reivewNo = Integer.parseInt(request.getParameter("reviewNo"));
		reviewService.deleteReview(reivewNo);
		
		request.getRequestDispatcher("/WEB-INF/views/product_review/message/reviewDeleted.jsp").forward(request, response);
	}
}	
