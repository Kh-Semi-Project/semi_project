package mvc.sale_product.product.controller.productwriting;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.sale_product.product.model.service.ProductService;
import mvc.sale_product.product.model.vo.ProductWritingQuestion;

/**
 * Servlet implementation class ProductWritingCommentInsertServlet
 */
@WebServlet("/sale_product/productWritingCommentInsert")
public class ProductWritingCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService ps = new ProductService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		<form 
			action="" 
			name="commentInsertFrm" 
			method="POST">					
			<input type="hidden" name="commentLevel" value="1" />
			<!-- 아이디는 session에서 가져와서 수정 필요 -->
			<input type="hidden" name="writer" value="test0" />
			<input type="hidden" name="product_writing_code" value="<%= pw.getProduct_writing_code()%>" />
			<input type="hidden" name="commentRef" value="0" />
			<input type="hidden" name="comment_secret" value="0" /> <!-- 0이면 공개댓글, 1이면 비밀댓글 -->
		</form>
		 */
		// 1. 사용자입력 값처리
		request.setCharacterEncoding("utf-8"); // 값이 깨져서 추가
		String writer = request.getParameter("writer");
		String comment_content = request.getParameter("comment_content");
		int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
		int product_writing_code = Integer.parseInt(request.getParameter("product_writing_code"));
		int commentRef = Integer.parseInt(request.getParameter("commentRef"));
		int comment_secret = Integer.parseInt(request.getParameter("comment_secret"));
		
		ProductWritingQuestion pwq = new ProductWritingQuestion();
		pwq.setId(writer);
		pwq.setComment_content(comment_content);
		pwq.setComment_level(commentLevel);
		pwq.setProduct_writing_code(product_writing_code);
		pwq.setComment_ref(commentRef);
		pwq.setComment_secret(comment_secret == 0 ? "n":"y");
		
		// 2. 업무로직
		int result = ps.InsertProductComment(pwq);
		System.out.println(result > 0 ? "댓글 등록 성공":"댓글 등록 실패");
		// 3. 응답처리
		String location = request.getHeader("Referer");
	
		response.sendRedirect(location);
	}

}
