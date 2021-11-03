package mvc.sale_product.product.controller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.sale_product.product.model.service.ProductService;
import mvc.sale_product.product.model.vo.ProductBuy;

/**
 * Servlet implementation class ProductBuyServlet
 */
@WebServlet("/sale_product/productBuy")
public class ProductBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService pws = new ProductService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//제품 구매를 클릭했을때 제품 구매 테이블에 추가하는 서블릿
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1.사용자입력 값처리
		ProductBuy pb = new ProductBuy();
		pb.setId(request.getParameter("buy_id"));
		pb.setPrice_sum(Integer.parseInt(request.getParameter("price_sum")));
		pb.setProduct_code(Integer.parseInt(request.getParameter("product_code")));
		pb.setProduct_buy_count(Integer.parseInt(request.getParameter("product_buy_count")));
		pb.setProduct_buy_price(Integer.parseInt(request.getParameter("product_buy_price")));
		pb.setProduct_donate_price(Integer.parseInt(request.getParameter("product_donate_price")));
		
		System.out.println("productBduyServlet@"+pb);
		
		// 2. 업무로직
		int result = pws.ProductBuy(pb);
		System.out.println("result@servlet = " + result);
		
		// 3. 응답 처리
		if(result > 0) { // 제품 구매에 성공했을때
			//주문했던 데이터 가져오는 서블릿으로 이동
			String location = request.getContextPath() + "/sale_product/productBuyList?id="+pb.getId();
			
			response.sendRedirect(location);
		}
		else { // 제품 구매에 실패했을 때
			System.out.println("제품구매실패");
//			String location = request.getHeader("Referer");
			String location = request.getContextPath() + "/sale_product/productwritingList?category=0";
			
			response.sendRedirect(location);
			
		}	
	}

}
