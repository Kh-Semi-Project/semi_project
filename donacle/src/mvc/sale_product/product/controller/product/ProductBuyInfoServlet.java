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
 * Servlet implementation class ProductBuy
 */
@WebServlet("/sale_product/productBuyInfo")
public class ProductBuyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService pws = new ProductService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//제품을 구매하기 눌렀을때 해당 구매 정보 출력하기 위해 정보 가져오는 서블릿
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
			<input type="hidden" id = "product_code" value="<%= pw.getProduct_writing_code()%>"/>
			<input type="hidden" id = "product_buy_count" value="1"/>
			<input type="hidden" id = "sum_price" value ="<%= pw.getProduct_price()%>"/>
		 */
		// 1. 사용자입력 값 처리
		int product_code = Integer.parseInt(request.getParameter("product_code"));
		System.out.println("product_code@"+product_code);
		int product_buy_count = Integer.parseInt(request.getParameter("product_buy_count"));
		String id = request.getParameter("seller_id");
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginMember");
		if(member == null) {
			String location = request.getContextPath() + "/";
			
			response.sendRedirect(location);
		}else {
			String cos_id = member.getId(); // 아이디 가져오기 필요
			int sum_price = Integer.parseInt(request.getParameter("sum_price"));
			int product_buy_price = (int)(sum_price * 0.9);//제품 가격의 90%만 저장
			int product_donate_price = sum_price - product_buy_price;//제품 가격의 10% 후원 금액으로 저장
			
			// 2. 업무로직
			ProductBuy pb = new ProductBuy();
			pb.setProduct_code(product_code);
			pb.setProduct_buy_count(product_buy_count);
			pb.setProduct_buy_price(product_buy_price);
			pb.setPrice_sum(product_buy_price + product_donate_price);
			pb.setProduct_donate_price(product_donate_price);
			pb.setId(id);	
			ProductBuy productBuy = pws.ProductBuyInfo(pb); // 제품 구매를 하고 나서 해당 제품 정보
			mvc.login_join_and_management.model.vo.Address address = pws.MemberAddress(cos_id);
			
			// 3. 응답처리
			request.setAttribute("ProductBuyInfo", productBuy);
			request.setAttribute("address", address);
			System.out.println("productBuyInfo@servlet@"+productBuy);
			request
				.getRequestDispatcher("/WEB-INF/views/sale_product/consumer/productBuy.jsp")
				.forward(request,  response);
		}
	}
	//장바구니 쪽에서 주문 클릭했을 때 get 방식 추가

}
