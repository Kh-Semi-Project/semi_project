package mvc.sale_product.product.controller.product;

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
 * Servlet implementation class ProductBuyListServlet
 */
@WebServlet("/sale_product/productBuyList")
public class ProductBuyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService pws = new ProductService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//제품 구매 리스트 출력하는 서블릿 -> 마이페이지 쪽에서도 출력하기 위해 따로 뺌
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// id를 포함시켜서 보내야지 됨
		// 1. 사용자입력 값처리
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginMember");
		if(member == null) {
			String location = request.getContextPath() + "/";			
			response.sendRedirect(location);
		}
		String id = member.getId(); // 아이디 가져오기 필요
		// 2. 업무로직
		List<ProductBuy> ProductBuyList = pws.ProductBuyList(id);
		System.out.println("구매 제품 리스트" + ProductBuyList);
		
		// 3. 응답처리
		request.setAttribute("ProductBuyList", ProductBuyList);
		request
			.getRequestDispatcher("/WEB-INF/views/sale_product/consumer/productOrderCheckList.jsp")
			.forward(request, response);
	}

}
