package mvc.sale_product.product.controller.productwriting;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.sale_product.product.model.service.ProductService;

/**
 * Servlet implementation class ProductWritingAdminCheckUpdateServlet
 */
@WebServlet("/sale_product/productWritingAdminCheckUpdate")
public class ProductWritingAdminCheckUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService ps = new ProductService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자요청 값처리
		int product_writing_code = Integer.parseInt(request.getParameter("product_writing_code"));
		System.out.println("product_writing_code@"+product_writing_code);
		// 2. 업무로직
		int result = ps.updateProductWritingAdminCheck(product_writing_code);
		System.out.println(result > 0 ? "관리자 승인 변경 성공" : "관리자 승인 변경 실패");
		
		// 3. 응답 처리
		String location = request.getContextPath() + "/sale_product/productWritingAdminCheckList";
		
		response.sendRedirect(location);
		
	}

}
