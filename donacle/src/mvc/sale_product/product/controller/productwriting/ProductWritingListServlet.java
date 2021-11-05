package mvc.sale_product.product.controller.productwriting;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.login_join_and_management.model.vo.Member;
import mvc.sale_product.common.MvcUtils;
import mvc.sale_product.product.model.service.ProductService;
import mvc.sale_product.product.model.vo.ProductWriting;


/**
 * Servlet implementation class product_writing_List
 */
@WebServlet("/sale_product/productwritingList")
public class ProductWritingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService pws = new ProductService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	// 제품 판매글 전체/특정 리스트 출력
	// 해당 서블릿을 이용하기 위해서는 category를 같이 보내야 됨
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자입력값처리 cPage numPerPage = 9
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		if(member == null) {
			String msg = "로그인 후 이용 가능합니다.";
			request.setAttribute("msg", msg);

		}else {
			int cPage = 1;
			int numPerPage = 6;
			int category = Integer.parseInt(request.getParameter("category"));
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch(NumberFormatException e) {
				
			}
			
			// 2.업무로직
			// a.content영역 - paging query
			int start = cPage * numPerPage - (numPerPage - 1);
			int end = cPage * numPerPage;
			List<ProductWriting> list = pws.selectProductWritingList(start,end,category);
			List<ProductWriting> toplist = pws.selectProductWritingTop3List(category);
			System.out.println("list@servlet = " + list);
	//		
	//		// b.pagebar영역 
	//		// totalContents, url, category 준비
			int totalContents = pws.selectTotalContents(category);
			System.out.println("totalContents@"+totalContents);
			String url = request.getRequestURI();
			String pagebar = MvcUtils.getPagebar(cPage,  numPerPage, totalContents, url, category);
			System.out.println("pagebar@servlet = " + pagebar);
			
	//		// 3. 응답처리 (view단 forwarding)
			request.setAttribute("productWritinglist", list);
			request.setAttribute("productWritingtoplist", toplist);
			request.setAttribute("pagebar", pagebar);
		}
		request.setAttribute("loginMember", member);
		request
			.getRequestDispatcher("/WEB-INF/views/sale_product/common/productWritingList.jsp")
			.forward(request,  response);
	}

}
