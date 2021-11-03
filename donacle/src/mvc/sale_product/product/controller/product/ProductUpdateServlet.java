package mvc.sale_product.product.controller.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.sale_product.product.model.service.ProductService;
import mvc.sale_product.product.model.vo.Product;

/**
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/sale_product/productUpdate")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService ps = new ProductService();
	
	//제품 정보 수정하기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 사용자입력 값처리
		request.setCharacterEncoding("UTF-8"); // jsp에서 servlet으로 값 전달시 한글깨져서 추가
		
		String id = request.getParameter("sale_id");
		String productName = request.getParameter("productName");
		int productCode = Integer.parseInt(request.getParameter("productCode"));
		int category_code = Integer.parseInt(request.getParameter("category_code"));
		String productContent = request.getParameter("productContent");
		int productPrice = Integer.parseInt(request.getParameter("productPrice"));
		int shipping_fee = Integer.parseInt(request.getParameter("shipping_fee"));
		int pro_Counts = Integer.parseInt(request.getParameter("pro_Counts"));
		String img_url = request.getParameter("img_url");
		
		Product p = new Product();
		p.setCategory_code(category_code);
		p.setProduct_code(productCode);
		p.setProduct_name(productName);
		p.setProduct_price(productPrice);
		p.setProduct_img(img_url);
		p.setProduct_content(productContent);
		p.setProduct_count(pro_Counts);
		p.setShipping_fee(shipping_fee);
		p.setId(id);
		
		// 2. 업무로직
		System.out.println("updateServlet@"+p);
		int result = ps.updateProduct(p);
		System.out.println(result > 0 ? "제품정보수정성공": "제품정보수정실패");
		int result_admin = ps.updateProductAdmin(p);
		System.out.println(result_admin > 0 ? "관리자권한수정성공": "관리자권한수정실패");
		
		// 3. 응답처리
		// 후에 id는 변경 필요
		String location = request.getContextPath() + "/sale_product/productList?id="+p.getId();
		
		response.sendRedirect(location);
	}

}
