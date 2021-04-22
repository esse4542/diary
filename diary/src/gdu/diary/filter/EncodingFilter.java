package gdu.diary.filter;

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

	// 생성자
    public EncodingFilter() {

    }
    
    // 필터 시작시
	public void init(FilterConfig fConfig) throws ServletException {

	}
	
    // 필터 종료시
	public void destroy() {

	}

	// 필터 메서드
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 타겟요청 실행전
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
		// 타겟요청 실행후
	}

}