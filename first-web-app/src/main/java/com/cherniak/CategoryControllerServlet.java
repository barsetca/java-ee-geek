package com.cherniak;

import com.cherniak.persist.Category;
import com.cherniak.persist.CategoryRepository;
import com.cherniak.persist.Product;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(urlPatterns = "/category/*")
public class CategoryControllerServlet extends HttpServlet {

  private static final Pattern pathParam = Pattern.compile("\\/(\\d*)$");

  private CategoryRepository categoryRepository;

  @Override
  public void init() throws ServletException {
    categoryRepository = (CategoryRepository) getServletContext()
        .getAttribute("categoryRepository");
  }

  @Override
  protected void doGet(
      HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.getPathInfo() == null || req.getPathInfo().equals("") || req.getPathInfo()
        .equals("/")) {
      req.setAttribute("categories", categoryRepository.findAll());
      getServletContext().getRequestDispatcher("/WEB-INF/views/category.jsp").forward(req, resp);
    } else if (req.getPathInfo().equals("/new")) {
      req.setAttribute("category", new Category());
      getServletContext().getRequestDispatcher("/WEB-INF/views/category_form.jsp").forward(req, resp);
    } else {
      Matcher matcher = pathParam.matcher(req.getPathInfo());
      if (matcher.matches()) {
        long id;
        try {
          id = Long.parseLong(matcher.group(1));
        } catch (NumberFormatException ex) {
          resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
          return;
        }
        Category category = categoryRepository.findById(id);
        if (category == null) {
          resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        req.setAttribute("category", category);
        getServletContext().getRequestDispatcher("/WEB-INF/views/category_form.jsp")
            .forward(req, resp);
      }
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (req.getPathInfo() == null || req.getPathInfo().equals("") || req.getPathInfo()
        .equals("/")) {

      String strId = req.getParameter("id");
      try {
        Category category = new Category(
            strId.isEmpty() ? null : Long.parseLong(strId),
            req.getParameter("name")
        );
        categoryRepository.save(category);
        resp.sendRedirect(getServletContext().getContextPath() + "/category");
      } catch (NumberFormatException ex) {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }
    } else {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
  }
}
