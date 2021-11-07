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
 * Servlet implementation class ProductWritingCommentDeleteServlet
 */
@WebServlet("/sale_product/CommentDelete")
public class ProductWritingCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService ps = new ProductService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력 값처리
		request.setCharacterEncoding("utf-8"); // 값이 깨져서 추가
		int product_question_code = Integer.parseInt(request.getParameter("product_question_code"));
		int product_writing_code = Integer.parseInt(request.getParameter("product_writing_code"));
		int product_code = Integer.parseInt(request.getParameter("product_code"));

		// 2. 업무로직
		int result = ps.DeleteProductComment(product_question_code);
		System.out.println(result > 0 ? "댓글 등록 성공":"댓글 등록 실패");
		// 3. 응답처리
		String location = request.getContextPath() + "/sale_product/ProductWritingView?code="+product_writing_code+"&&pcode="+product_code;
		
		response.sendRedirect(location);
	}

}
