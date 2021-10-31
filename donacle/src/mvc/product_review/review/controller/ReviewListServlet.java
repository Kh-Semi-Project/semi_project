package mvc.product_review.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.product_review.common.MvcUtils;
import mvc.product_review.review.model.service.ReviewService;
import mvc.product_review.review.model.vo.Review;


/**
 * Servlet implementation class BoardCommentEnrollServlet
 */
@WebServlet("/review/reviewList")
public class ReviewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReviewService reviewService = new ReviewService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		
		int start = (page-1) * 10;
		int end = 10*page;
		
		List<Review> list = reviewService.selectReviewList(start, end);
		int num = reviewService.selectReviewListCnt();
	
		String pagebar = MvcUtils.getPagebar(page, 10, num, request.getRequestURI());
		request.setAttribute("reviewList", list);
		request.setAttribute("reviewListCnt", num);
		request.setAttribute("pagebar", pagebar);
		request.getRequestDispatcher("/WEB-INF/views/product_review/reviewList.jsp").forward(request, response);;
	}
}	
