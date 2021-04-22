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

	// ������
    public EncodingFilter() {

    }
    
    // ���� ���۽�
	public void init(FilterConfig fConfig) throws ServletException {

	}
	
    // ���� �����
	public void destroy() {

	}

	// ���� �޼���
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Ÿ�ٿ�û ������
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
		// Ÿ�ٿ�û ������
	}

}