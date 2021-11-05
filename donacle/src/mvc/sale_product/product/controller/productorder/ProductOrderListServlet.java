package mvc.sale_product.product.controller.productorder;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.login_join_and_management.model.vo.Member;
import mvc.sale_product.product.model.service.ProductService;
import mvc.sale_product.product.model.vo.ProductBuy;

/**
 * Servlet implementation class ProductOrderListServlet
 */
@WebServlet("/sale_product/productOrderList")
public class ProductOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService ps = new ProductService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//제품 주문 리스트 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// String id = request.getParameter("id");// 아이디 값 가져오기
		// 아직 통합작업이 되지 않아 test로 작업
		// 1. 사용자 입력 값처리
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginMember");
		if(member == null) {
			String location = request.getContextPath() + "/";
			
			response.sendRedirect(location);
		}
		String id = member.getId(); // 아이디 가져오기 필요
		
		// 2. 업무로직
		List<ProductBuy> pbList = ps.productOrderList(id);
		
		// 3. 
		
		request.setAttribute("OrderProductList", pbList);
		System.out.println("productOrderList@" + pbList);
		
		request.setAttribute("ProductOrderList", pbList);
		request
			.getRequestDispatcher("/WEB-INF/views/sale_product/seller/productOrderList.jsp")
			.forward(request, response);
	}

}
