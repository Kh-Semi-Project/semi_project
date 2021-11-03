package mvc.login_join_and_management.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.login_join_and_management.model.service.MemberService;
import mvc.login_join_and_management.model.vo.Member;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/admin/*")
public class AdminFilter implements Filter {

   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// servlet 응답 전
		// login 여부 확인
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");

		if (loginMember == null || !(MemberService.MEMBER_KIND_ADMIN.equals(loginMember.getKind()))) {
			session.setAttribute("msg", "관리자만 접근이 가능합니다.");
			HttpServletResponse httpRes = (HttpServletResponse) response;
			httpRes.sendRedirect(httpReq.getContextPath() + "/");
			return;
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);

		// servlet 응답 후
	}
}
