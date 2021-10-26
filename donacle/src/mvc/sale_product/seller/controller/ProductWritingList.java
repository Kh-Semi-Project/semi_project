package mvc.sale_product.seller.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.sale_product.common.MvcUtils;
import mvc.sale_product.seller.model.service.ProductWritingService;
import mvc.sale_product.seller.model.vo.ProductWriting;


/**
 * Servlet implementation class product_writing_List
 */
@WebServlet("/product_writing_List")
public class ProductWritingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductWritingService pws = new ProductWritingService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자입력값처리 cPage numPerPage = 10
		int cPage = 1;
		int numPerPage = 9;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			
		}
		// 2.업무로직
		// a.content영역 - paging query
		int start = cPage * numPerPage - (numPerPage - 1);
		int end = cPage * numPerPage;
		List<ProductWriting> list = pws.selectProductWritingList(start,end);
		System.out.println("list@servlet = " + list);
//		
//		// b.pagebar영역 
//		// totalContents, url 준비
		int totalContents = pws.selectTotalContents();
		String url = request.getRequestURI();
		String pagebar = MvcUtils.getPagebar(cPage,  numPerPage, totalContents, url);
		System.out.println("pagebar@servlet = " + pagebar);
		
//		// 3.view단 forwarding
		request.setAttribute("list", list);
		request.setAttribute("pagebar", pagebar);
		request
			.getRequestDispatcher("/WEB-INF/views/sale_product/common/productWritingList.jsp")
			.forward(request,  response);
	}

}
