package mvc.sale_product.product.controller.product;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.sale_product.product.model.service.ProductService;

/**
 * Servlet implementation class ProductBuyCancelServlet
 */
@WebServlet("/sale_product/productBuyDelete")
public class ProductBuyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService pws = new ProductService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String [] codes = request.getParameterValues("codes");
		//배열로 보내서 배열로 받으려고 했는데 배열로 보내도 String 형식으로 보내져서 그냥 필요없는 부분 지우고 배열에 저장
		String[] codes = request.getParameter("codes").replace("\"","").replace("[","").replace("]","").split(",");
//		System.out.println("productbuyCancel@servlet@codes@" + Arrays.toString(codes));
		int result = pws.ProductBuyCancel(codes);
		System.out.println("productBuyDelete@servlet@result@" + result);
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print("");
	}

}
