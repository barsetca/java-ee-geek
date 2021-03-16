package com.cherniak;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirstServlet implements Servlet {

  private ServletConfig config;

  @Override
  public void init(ServletConfig config) throws ServletException {
    log.info("Servlet initialized");
    this.config = config;
  }

  @Override
  public ServletConfig getServletConfig() {
    return config;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    log.info("service - New Request");
    res.getWriter().println("<h1>Hello new deploy from my FirstServlet</h1>");
    res.getWriter().println("<h2>Привет!!!</h2>");
  }

  @Override
  public String getServletInfo() {
    return null;
  }

  @Override
  public void destroy() {

  }
}
