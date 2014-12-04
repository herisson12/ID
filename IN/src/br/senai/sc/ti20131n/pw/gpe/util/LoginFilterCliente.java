package br.senai.sc.ti20131n.pw.gpe.util;

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

import br.senai.sc.ti20131n.pw.gpe.mb.LoginMB;

@WebFilter(urlPatterns = "/cliente/*")
public class LoginFilterCliente implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		LoginMB loginMB = (LoginMB) httpServletRequest.getSession()
				.getAttribute("loginMB");

		if (loginMB == null || !loginMB.logado()) {
			((HttpServletResponse) response).sendRedirect(httpServletRequest
					.getContextPath().concat("/index.xhtml"));
		}

		filterChain.doFilter(request, response);

	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
