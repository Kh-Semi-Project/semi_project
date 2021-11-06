package mvc.sale_product.product.controller.productwriting;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.sale_product.product.model.service.ProductService;
import mvc.sale_product.product.model.vo.ProductWriting;
import mvc.sale_product.product.model.vo.ProductWritingQuestion;
import mvc.sale_product.common.MvcUtils;
/**
 * Servlet implementation class ProductWritingView
 *////sale_product/productwritingList
@WebServlet("/sale_product/ProductWritingView")
public class ProductWritingViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService pws = new ProductService();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//판매글 클릭했을때 출력하는 서블릭
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력 값처리
		int pw_code = Integer.parseInt(request.getParameter("code")); //제품 상세 내용을 보려는 글의 코드
		int p_code = Integer.parseInt(request.getParameter("pcode")); //제품 상세 내용을 보려는 글의 코드
		
		// 비지니스로직 호출
		Cookie[] cookies = request.getCookies();
		boolean hasRead = false;
		String pw_value = "";
		
		if(cookies != null) {
			for(Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();
				System.out.println("cookies = " + name + " : " + value);
				
				if("pw_code".equals(name)) {
					pw_value = value;
					// 현재 게시글 읽음여부
					if(value.contains("|" + pw_code + "|")) {
						hasRead = true;
					}
					break;
				}
			}
		}
		System.out.printf("hasRead = %b, pw_value = %s%n", hasRead, pw_value);
		
		//게시글을 처음 읽는 경우
		if(!hasRead) {
			// 게시글 Cookie
			Cookie cookie = new Cookie("pw_code", pw_value + "|" + pw_code + "|");
			cookie.setMaxAge(24 * 60 * 60); // 하루 동안 쿠키 저장
			cookie.setPath(request.getContextPath() + "/sale_product/ProductWritingView"); // 해당 요청시만 cookie전송
			response.addCookie(cookie);
			
			//조회수 증가
			int result = pws.updateReadCount(pw_code);	
			System.out.println("updateReadCount@" + result);
			}
			//게시글 하나 가져오기
			ProductWriting pw = pws.selectOneProductWriting(p_code);
			System.out.println(pw);
			
			
			//게시글 가져오기에 실패한경우
			if(pw == null){
				request.getSession().setAttribute("msg", "조회한 제품 판매글이 존재하지 않습니다.");
				response.sendRedirect(request.getContextPath() + "/sale_product/productwritingList");
				return;
			}
			
			// XSS공격대비 (cross-site script공격)
			String content = MvcUtils.escapeHtml(pw.getProduct_content());
			
			// 개행문자 br태그 변환처리
			content = MvcUtils.convertLineFeedToBr(content);
			
			
			pw.setProduct_content(content);
			
			// 이부분은 미완성
			// 문의 댓글 jsp에 추가해야 됨
			// 문의 댓글 가져오기
			List<ProductWritingQuestion> commentList = pws.selectCommentList(pw_code);
			System.out.println("commentList@servlet = " + commentList);
			
			
			//3. 응답 처리(view단 처리위임)
			request.setAttribute("product_writing", pw);
			request.setAttribute("commentList", commentList);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/sale_product/common/productWritingView.jsp");
			reqDispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
