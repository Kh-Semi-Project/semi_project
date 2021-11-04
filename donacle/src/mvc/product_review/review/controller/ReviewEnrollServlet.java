package mvc.product_review.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import mvc.login_join_and_management.model.vo.Member;
import mvc.product_review.common.FileUploadPath;
import mvc.product_review.common.MvcFileRenamePolicy;
import mvc.product_review.common.vo.Attach;
import mvc.product_review.review.model.service.ReviewService;
import mvc.product_review.review.model.vo.Review;


/**
 * Servlet implementation class BoardCommentEnrollServlet
 */
@WebServlet("/review/reviewEnroll")
public class ReviewEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReviewService reviewService = new ReviewService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		String saveDirectory = application.getRealPath("/upload");
		MultipartRequest multipartRequest = new MultipartRequest(
				request, saveDirectory,
						1024 * 1024 * 50, 
						"utf-8", 
						new MvcFileRenamePolicy()
					);
		//String id = ((Member) request.getSession().getAttribute("loginMember") ).getId("loginMember");
		Member loginMember = ((Member)request.getSession().getAttribute("loginMember"));
		String loginId = null;
		if(loginMember != null) {
			loginId = loginMember.getId();
		}
		System.out.println("테스트"+loginId);

		//String id = "test0"; //세션정보 알아내서 수정하기
		
		Review review = new Review();
		review.setId(loginId);
		review.setProductBuyCode(Integer.parseInt(multipartRequest.getParameter("productBuyCode")));
		review.setReviewContent(multipartRequest.getParameter("reviewContent"));
		review.setReviewTitle(multipartRequest.getParameter("reviewTitle"));
		
		String originalFilename = multipartRequest.getOriginalFileName("upFile");
		String renamedFilename = multipartRequest.getFilesystemName("upFile");
		
		if(multipartRequest.getFile("upFile") != null && originalFilename != null && renamedFilename != null) {
			Attach attach = new Attach();
			attach.setOriginalFilename(originalFilename);
			attach.setRenamedFilename(renamedFilename);
			review.setAttach(attach);
		}
		
		reviewService.insertReview(review);
		request.getRequestDispatcher("/WEB-INF/views/product_review/message/enrollSuccess.jsp").forward(request, response);
	}
}	
