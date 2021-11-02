package mvc.sale_product.product.controller.productorder;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.sale_product.product.model.service.ProductService;

/**
 * Servlet implementation class ProductOrderCheckServlet
 */
@WebServlet("/sale_product/productOrderCheck")
public class ProductOrderCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService pws = new ProductService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//사용자가 제품 수령완료 버튼을 눌렀을때 실행되는 서블릿
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		getProduct_buy_code
//		getProduct_shipping_status
		
		// 1. 사용자입력 값처리
		int Product_buy_code = Integer.parseInt(request.getParameter("product_buy_code"));
		int Product_donate_price = Integer.parseInt(request.getParameter("product_donate_price"));
		
		// 2. 업무로직
		System.out.println("pbcode = "+Product_buy_code);
		int result = pws.updateProductOrderCheck(Product_buy_code);
		String msg = result > 0 ? "배송 수령 컬럼 성공!" : "배송 수령 컬럼 실패!";
		System.out.println("productOrderServlet@"+result);
		
		// 3. 응답 처리
		
		//미완료
		//------------------------------------------
		// 주문완료되면 해당 제품 테이블에서 count update 필요!
		//------------------------------------------
		
		// 해당 아이디는 session에 저장된 것 가져와서 넣기
		// 일단 test 버전
		String id = "test0";
		if(result > 0) {
			result = pws.insertDonate(Product_donate_price, id);
			System.out.println("productOrderServlet@"+result);
		}
		String location = request.getContextPath() + "/sale_product/productBuyList?id="+id;
		response.sendRedirect(location);
	}

}
