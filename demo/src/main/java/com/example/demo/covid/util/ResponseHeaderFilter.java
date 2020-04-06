package com.example.demo.covid.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class ResponseHeaderFilter implements Filter {


	@Autowired
	private Environment env;

	/**
	 * @method To add the Allow Access Control Origin headers to the all responses
	 * @param request  - Servlet Request
	 * @param response - Servlet Response
	 * @param chain    - Filter Chain
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String url = env.getProperty("allow.cross.origin");

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-Control-Allow-Origin", url);
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		httpServletResponse.setHeader("Access-Control-Allow-Methods","GET, POST, DELETE, OPTIONS");
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
		chain.doFilter(request, response);
	}

}
