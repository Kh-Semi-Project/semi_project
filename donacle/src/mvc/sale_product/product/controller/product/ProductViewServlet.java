package mvc.sale_product.product.controller.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.sale_product.product.model.service.ProductService;
import mvc.sale_product.product.model.vo.ProductWriting;

/**
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/sale_product/productView")
public class ProductViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService ps = new ProductService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력 값처리
		int product_code = Integer.parseInt(request.getParameter("product_code"));
		System.out.println("ProductViewServlet@"+product_code);
		
		// 2. 업무로직
		// 제품 글 가져오기
		ProductWriting pw = ps.selectOneProduct(product_code);
		
		//응답처리
		request.setAttribute("ProductWriting", pw);
		System.out.println("productView@servlet@"+pw);
		request
			.getRequestDispatcher("/WEB-INF/views/sale_product/seller/productUpdate.jsp")
			.forward(request,  response);
	}

}
