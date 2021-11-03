package mvc.product_review.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import mvc.product_review.common.FileUploadPath;
import mvc.product_review.common.MvcFileRenamePolicy;
import mvc.product_review.common.model.service.AttachService;
import mvc.product_review.common.vo.Attach;
import mvc.product_review.review.model.service.ReviewService;
import mvc.product_review.review.model.vo.BuyLog;
import mvc.product_review.review.model.vo.Review;


/**
 * Servlet implementation class BoardCommentEnrollServlet
 */
@WebServlet("/review/reviewUpdate")
public class ReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReviewService reviewService = new ReviewService();
	private AttachService attachService = new AttachService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest multipartRequest = new MultipartRequest(
				request, 
				FileUploadPath.FILE_SAVE_PATH, 
				1024 * 1024 * 10, 
				"utf-8", 
				new MvcFileRenamePolicy()
			);

		Review review = new Review();
		review.setReviewNo(Integer.parseInt(multipartRequest.getParameter("reviewNo")));
		review.setReviewContent(multipartRequest.getParameter("reviewContent"));
		review.setReviewTitle(multipartRequest.getParameter("reviewTitle"));
		
		String originalFilename = multipartRequest.getOriginalFileName("upFile");
		String renamedFilename = multipartRequest.getFilesystemName("upFile");
		
		
		if("on".equals(multipartRequest.getParameter("originDelete"))) {
			attachService.deleteAttach(review.getReviewNo());
		}
		
		if(multipartRequest.getFile("upFile") != null && originalFilename != null && renamedFilename != null) {
			attachService.deleteAttach(review.getReviewNo());
			Attach attach = new Attach();
			attach.setOriginalFilename(originalFilename);
			attach.setRenamedFilename(renamedFilename);
			attach.setReviewNo(review.getReviewNo());
			review.setAttach(attach);
		}
		
		reviewService.updateReview(review);
		request.getRequestDispatcher("/WEB-INF/views/product_review/message/updateSuccess.jsp").forward(request, response);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// String id 
		
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		
		Review review = reviewService.selectReview(reviewNo);
		request.setAttribute("review", review);
		request.getRequestDispatcher("/WEB-INF/views/product_review/reviewUpdateForm.jsp").forward(request, response);
		
	}
}	
