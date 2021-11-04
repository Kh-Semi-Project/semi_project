package mvc.login_join_and_management.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class EncodingFilter implements Filter {

	/**
	 * FilterChain 에서의 실행 순서
	 * 1. web.xml에 선언된 순서
	 * 2. @webFilter annotation을 사용하는 경우, 필터 이름 순서로 처리
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 사용자 입력값에 대한 encoding 처리 / servlet 가기 전
		request.setCharacterEncoding("utf-8");
//		System.out.println("[utf-8 인코딩 처리]");
		chain.doFilter(request, response);
		
		// 응답메세지 / servlet 다녀 온 후
	}

	

}
