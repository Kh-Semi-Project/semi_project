package mvc.sale_product.product.controller.productwriting;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.sale_product.product.model.service.ProductService;
import mvc.sale_product.product.model.vo.ProductWriting;

/**
 * Servlet implementation class ProductWritingAdminCheckListServlet
 */
@WebServlet("/sale_product/productWritingAdminCheckList")
public class ProductWritingAdminCheckListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService ps = new ProductService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	// 관리자 승인이 필요한 리스트
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 2. 업무로직
		List<ProductWriting> pw = ps.productWritingAdminCheckList();
		System.out.println("ProductList@servlet@" + pw);
		
		// 3. 응답처리
		request.setAttribute("ProductWritingAdminList", pw);
		request
			.getRequestDispatcher("/WEB-INF/views/sale_product/admin/AdminCheckList.jsp")
			.forward(request,response);
	}

}
