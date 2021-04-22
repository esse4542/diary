package gdu.diary.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/auth/*")
public class AuthFilter implements Filter { // /Auth/IndexController -> 요청 1. EncodingFilter 2. AuthFilter 3. IndexController 4. AuthFilter 5. EncodingFilter

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 로그인이 되어있지 않은 상태에서 "/Auth/"문자로 시작하는 요청이 들어오면 redirect
		HttpServletRequest httpRequest = (HttpServletRequest)request; // ServletRequest는 HttpServletRequest의 부모타입이기 때문에 강제 형변환을 통해 사용해야 getSession()을 가져올 수 있다.
		HttpSession session = httpRequest.getSession();
		if(session.getAttribute("sessionMember") == null) {
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
			return;
		}
		chain.doFilter(request, response);
	}
	
	// 생성자
    public AuthFilter() {
    	
    }

    // 필터 종료시
	public void destroy() {
		
	}
	
	// 필터 시작시
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}