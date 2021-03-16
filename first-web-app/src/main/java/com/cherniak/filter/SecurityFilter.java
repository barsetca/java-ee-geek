package com.cherniak.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

@WebFilter(urlPatterns = "/admin/*")
@Slf4j
public class SecurityFilter implements Filter {

  private FilterConfig filterConfig;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    log.info("SecurityFilter");

    if (request.getParameter("username") != null) {
      chain.doFilter(request, response);
    } else {
      filterConfig.getServletContext().getRequestDispatcher("/access_denied")
          .forward(request, response);
    }
  }

  @Override
  public void destroy() {

  }
}
