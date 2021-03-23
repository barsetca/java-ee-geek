package com.cherniak;

import com.cherniak.persist.Product;
import com.cherniak.persist.ProductRepository;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

//@WebServlet(urlPatterns = "/product/*")
@Slf4j
public class ProductServlet extends HttpServlet {

  private ProductRepository productRepository;

  @Override
  public void init() throws ServletException {

    productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    log.info("ProductServlet");
    String id = req.getParameter("id");
    if (req.getQueryString() == null) {
      if (req.getPathInfo() != null) {
        String idPath = req.getPathInfo().replaceFirst("/", "");
        writeProductInfo(req, resp, idPath);
        return;
      }
      resp.getWriter().println("<table>");
      resp.getWriter().println("<tr>");
      resp.getWriter().println("<th>Id</th>");
      resp.getWriter().println("<th>Name</th>");
      resp.getWriter().println("<th>Description</th>");
      resp.getWriter().println("<th>Price</th>");
      resp.getWriter().println("</tr>");

      for (Product product : productRepository.findAll()) {
        resp.getWriter().println("<tr>");
        //resp.getWriter().println("<td><a href='" + req.getContextPath() + req.getServletPath() +  "?id=" + product.getId() + "'>" + product.getId() + "</a></td>");
        resp.getWriter().println("<td><a href='" + req.getContextPath() + req.getServletPath() + "/" + product.getId() + "'>" + product.getId() + "</a></td>");
        resp.getWriter().println("<td>" + product.getName() + "</td>");
        resp.getWriter().println("<td>" + product.getDescription() + "</td>");
        resp.getWriter().println("<td>" + product.getPrice() + "</td>");
        resp.getWriter().println("</tr>");
      }
      resp.getWriter().println("</table>");
      return;
    } else {
      if (id != null) {
        writeProductInfo(req, resp, id);
      }
    }
  }

  private void writeProductInfo(HttpServletRequest req, HttpServletResponse resp, String idPath) throws IOException {
    try {
      long id = Long.parseLong(idPath);
      Product product = productRepository.findById(id);
      if (product == null) {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        resp.getWriter().println("<h1>" + "BAD REQUEST STATUS: " + resp.getStatus() + "</h1" +
            "<hr/><p>Cause: Not found Product by id = " + idPath + "</p>");
        return;
      }
      resp.getWriter().println("<p>Product info</p>");
      resp.getWriter().println("<p>Id: " + product.getId() + "</p>");
      resp.getWriter().println("<p>Name: " + product.getName() + "</p>");
      resp.getWriter().println("<p>Description: " + product.getDescription() + "</p>");
      resp.getWriter().println("<p>Price: " + product.getPrice() + "</p>");
      resp.getWriter().println(
          "<td><a href='" + req.getContextPath() + req.getServletPath() + "'>Показать все продукты</a></td>");
      resp.getWriter().println("<p>Из другой ветки</p>");
      resp.setStatus(HttpServletResponse.SC_OK);
    } catch (NumberFormatException e) {
      log.info("Incorrect format id. Id must be number, but id = {}", idPath);
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      resp.getWriter().println("<h1>" + "BAD REQUEST STATUS: " + resp.getStatus() + "</h1" +
          "<hr/><p>Cause: Incorrect format id. Id must be number, but id = " + idPath + "</p>");
    }
  }
}
