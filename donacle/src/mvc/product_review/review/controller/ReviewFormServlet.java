package mvc.product_review.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.login_join_and_management.model.vo.Member;
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
		Member loginMember = ((Member)request.getSession().getAttribute("loginMember"));
		String loginId = null;
		if(loginMember != null) loginId = loginMember.getId();
		String id = "test0"; //세션 정보 알아내서 수정하기!
		System.out.println("테스트"+loginMember);
		
		List<BuyLog> list = reviewService.selectBuyLog(loginId);
		System.out.println("리뷰 데이터"+list);
		if(list != null && list.size() > 0) {
			request.setAttribute("buyLogList", list);
			request.getRequestDispatcher("/WEB-INF/views/product_review/reviewForm.jsp").forward(request, response);			
		}else {
			request.getRequestDispatcher("/WEB-INF/views/product_review/message/noPurchase.jsp").forward(request, response);
		}	
	}
}	
