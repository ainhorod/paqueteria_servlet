package com.ecodeup.articulo.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class autenticacion
 */
@WebFilter(filterName="Autenticacion", urlPatterns="/vista/")
public class Autenticacion implements Filter {

	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("Autenticacion initialized");
	}
	
    /**
     * Default constructor. 
     */
    public Autenticacion() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		this.context.log("Requested Resource::"+uri);
		
		HttpSession session = req.getSession(false);
		
		if(session == null && !(uri.endsWith("html/jsp") || uri.endsWith(""))){
			this.context.log("Unauthorized access request");
			res.sendRedirect("register.jsp");
		}else{
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */


	
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
