package mvc.donate_and_cart.cart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.donate_and_cart.cart.model.service.CartListService;
import mvc.donate_and_cart.cart.model.vo.CartList;
import mvc.sale_product.common.MvcUtils;

/**
 * Servlet implementation class CartListServlet
 */
@WebServlet("/CartList")
public class CartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartListService cartListservice = new CartListService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자 입력 값 처리
		
//		String id = (String) request.getAttribute("");
		String id = "test0";
				
		// 2.업무로직
			// a.content영역 - paging query
			
			List<CartList> list = cartListservice.selectCartList(id);
			request.setAttribute("cartList", list);
			
		// 3. view단
			request
			.getRequestDispatcher("/WEB-INF/views/donate_and_cart/cart_index.jsp")
			.forward(request,  response);
	}

}
