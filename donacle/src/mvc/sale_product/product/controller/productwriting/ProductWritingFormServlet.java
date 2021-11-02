package mvc.sale_product.product.controller.productwriting;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.sale_product.product.model.vo.Product;

/**
 * Servlet implementation class ProductWritingFormServlet
 */
@WebServlet("/sale_product/productWritingForm")
public class ProductWritingFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자입력 값처리		
		request.setCharacterEncoding("utf-8"); // 값이 깨져서 추가
		int pw_code = Integer.parseInt(request.getParameter("product_code"));
		String pw_name = request.getParameter("product_name");
		String pw_img = request.getParameter("product_img");
		
		// 데이터 처리
		Product p = new Product();
		p.setProduct_code(pw_code);
		p.setProduct_name(pw_name);
		p.setProduct_img(pw_img);
		
		request.setAttribute("pw_data", p);

		request
			.getRequestDispatcher("/WEB-INF/views/sale_product/seller/productWritingAdd.jsp")
			.forward(request, response);
	}

}
