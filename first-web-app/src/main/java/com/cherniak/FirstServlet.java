package com.cherniak;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstServlet implements Servlet {

  private static final Logger LOGGER = LoggerFactory.getLogger(FirstServlet.class);
  private ServletConfig config;

  @Override
  public void init(ServletConfig config) throws ServletException {
    LOGGER.info("Servlet initialized");
    this.config = config;
  }

  @Override
  public ServletConfig getServletConfig() {
    return config;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    LOGGER.info("service - New Request");
    res.getWriter().println("<h3>Hello from my FirstServlet</h3>");
  }

  @Override
  public String getServletInfo() {
    return null;
  }

  @Override
  public void destroy() {

  }
}
