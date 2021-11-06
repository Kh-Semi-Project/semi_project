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
import mvc.sale_product.product.model.vo.ProductWriting;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/sale_product/productList")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService ps = new ProductService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력 값처리
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginMember");
		System.out.println("member@"+member);
		if(member == null) { // 로그아웃이 되었을 때
			String location = request.getContextPath() + "/";
			response.sendRedirect(location);
		}else {
			String id = member.getId(); // 아이디 가져오기 필요
			// 2. 업무로직
			List<ProductWriting> pw = ps.productList(id);
			System.out.println("ProductList@servlet@" + pw);
			
			// 3. 응답처리
			request.setAttribute("ProductList", pw);
			request
				.getRequestDispatcher("/WEB-INF/views/sale_product/seller/productList.jsp")
				.forward(request,response);
		}
	}
}
