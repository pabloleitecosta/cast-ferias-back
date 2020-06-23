package br.com.cast.ferias.Cors;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import br.com.cast.ferias.Config.Property.CastProperty;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter{
	
	@Autowired
	private CastProperty castProperty;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		res.setHeader("Access-Control-Allow-Origin", castProperty.getOriginPermitida());
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		if("OPTIONS".equals(req.getMethod()) && castProperty.getOriginPermitida().equals(req.getHeader("Origin"))) {
			
			res.setHeader("Access-Control-Allow-Methods", "HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH");
			res.setHeader("Access-Control-Allow-Headers", "Authorization, Accept, Content-Type");
			res.setHeader("Access-Control-Max-Age", "3600");
			res.setHeader("Content-Type", "application/x-www-form-urlencoded");
			
			res.setStatus(HttpServletResponse.SC_OK);
		}else {
			chain.doFilter(req, res);			
		}
		System.out.println("Valor do Orign: "+castProperty.getOriginPermitida().equals(req.getHeader("Origin")));
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
