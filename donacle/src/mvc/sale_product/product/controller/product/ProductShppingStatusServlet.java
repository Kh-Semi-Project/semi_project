package mvc.sale_product.product.controller.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.login_join_and_management.model.vo.Member;
import mvc.sale_product.product.model.service.ProductService;

/**
 * Servlet implementation class ProductShppingStatusServlet
 */
@WebServlet("/sale_product/productShppingStatus")
public class ProductShppingStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService ps = new ProductService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자입력 값처리
		int product_buy_code = Integer.parseInt(request.getParameter("product_buy_code"));
		System.out.println("배송@ProductShppingStatus@Servlet@"+product_buy_code);
		// 판매자 아이디 필요
		// test버전
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginMember");
		System.out.println("member@"+member);
		if(member == null) {
			String location = request.getContextPath() + "/";
			
			response.sendRedirect(location);
		}else {
			String id = member.getId(); // 아이디 가져오기 필요
			
			
			// 2. 업무 로직
			int result = ps.updateproductShppingStatus(product_buy_code);
			System.out.println(result > 0 ? "배송시작 성공" : "배송시작 실패");
			
			// 3. 응답 처리
			String location = request.getContextPath() + "/sale_product/productOrderList?id="+id;
			
			response.sendRedirect(location);
		}
	
	}

}
