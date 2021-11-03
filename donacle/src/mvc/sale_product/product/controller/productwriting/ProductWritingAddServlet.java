package mvc.sale_product.product.controller.productwriting;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.sale_product.product.model.service.ProductService;
import mvc.sale_product.product.model.vo.Product;

/**
 * Servlet implementation class ProductWritingAddServlet
 */
@WebServlet("/sale_product/productWritingAdd")
public class ProductWritingAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService ps = new ProductService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//제품 추가 누르면 뜨는 화면
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력 값처리
		request.setCharacterEncoding("UTF-8"); // jsp에서 servlet으로 값 전달시 한글깨져서 추가
		
		int product_code = Integer.parseInt(request.getParameter("product_code"));
		//session에서 가져오기 필요
		String id = "test0";
		// 2. 업무로직
		int result = ps.ProductWritingAdd(product_code,id);
		System.out.println(result > 0 ? "성공": "실패");
		
		// 3. 응답처리
		// 후에 id는 변경 필요
		String location = request.getContextPath() + "/sale_product/productList?id="+id;
		
		response.sendRedirect(location);
	}

}
