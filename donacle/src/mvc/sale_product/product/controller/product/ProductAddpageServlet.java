package mvc.sale_product.product.controller.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductAddpageServlet
 */
@WebServlet("/sale_product/productAddpage")
public class ProductAddpageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session에서 가져오는거 필요
		String id = "test0";
		
		request
			.getRequestDispatcher("/WEB-INF/views/sale_product/seller/productAdd.jsp")
			.forward(request, response);
	}

}
