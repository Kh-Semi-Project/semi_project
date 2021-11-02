package mvc.sale_product.product.controller.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.sale_product.product.model.service.CartService;
import mvc.sale_product.product.model.service.ProductService;

/**
 * Servlet implementation class CartAddServlet
 */
@WebServlet("/sale_product/cartAdd")
public class CartAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartService pws = new CartService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 값처리
		int product_code = Integer.parseInt(request.getParameter("product_code"));
		int product_buy_count = Integer.parseInt(request.getParameter("product_buy_count"));
		String id = "test0"; // 아이디 가져오기 필요
		
		// 2. 업무로직
		// 장바구니 번호 가져오기
		int cartNo = pws.selectCartNo(id);
		System.out.println("cartAdd@"+cartNo);
		int result = pws.insertCart(cartNo,product_code,product_buy_count);
		System.out.println("result@"+result);
		//code보내야됨
		//category -> /sale_product/productwritingList?category=0
		String location = request.getContextPath() + "/sale_product/productwritingList?category=0";
		
		response.sendRedirect(location);
		
	}

}
