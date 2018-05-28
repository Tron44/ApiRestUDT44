package com.web.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author rarranz
 *Filtro Cors del servlet para permitir que el servidor reciba peticiones
 * http-rest/ajax
 */
public class FiltroCors implements Filter {

	/**
	 * Default constructor.
	 */
	public FiltroCors() {		
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		// Autorización para llamadas http
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
		
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Credentials", "true");
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods",
				"GET, OPTIONS, HEAD, PUT, POST");

		HttpServletResponse resp = (HttpServletResponse) servletResponse;

		// Para las distintas opciones http realizadas se responde con el estado
		// ACCEPTED
		if (request.getMethod().equals("OPTIONS")) {
			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}
		chain.doFilter(request, servletResponse);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//
	}

}