package mvc.sale_product.product.controller.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mvc.sale_product.product.model.service.ProductService;

/**
 * Servlet implementation class ProductDeleteServlet
 */
@WebServlet("/sale_product/productDelete")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService pws = new ProductService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//주문 취소
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] codes = request.getParameter("codes").replace("\"","").replace("[","").replace("]","").split(",");
//		System.out.println("productbuyCancel@servlet@codes@" + Arrays.toString(codes));
		System.out.println(codes);
		int result_count = pws.selectProductynList(codes);
		String msg = "";
		if(result_count == 0) {
			msg ="배송출발 혹은 주문수령 되지않은 제품이 있습니다.\n삭제 할 수 없습니다.";
		}else {
			msg = result_count > 0 ? "제품 삭제에 성공했습니다." : "제품 삭제에 실패했습니다.";
		}
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(msg);	
		
	}

}
