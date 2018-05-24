package com.beyond.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.beyond.utils.HibernateUtils;

/**
 * Servlet Filter implementation class CreateSessionInView
 */
public class CreateSessionInView implements Filter {

	/**
	 * Default constructor.
	 */
	public CreateSessionInView() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		EntityManager em = HibernateUtils.getEntityManager();
		em.getTransaction().begin();

		// pass the request along the filter chain
		chain.doFilter(request, response);

		em.getTransaction().commit();

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
