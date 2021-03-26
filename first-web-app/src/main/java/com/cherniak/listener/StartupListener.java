package com.cherniak.listener;

import com.cherniak.persist.Category;
import com.cherniak.persist.CategoryRepository;
import com.cherniak.persist.Customer;
import com.cherniak.persist.CustomerRepository;
import com.cherniak.persist.Product;
import com.cherniak.persist.ProductRepository;
import java.math.BigDecimal;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class StartupListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    log.info("StartupListener");
    ServletContext servletContext = sce.getServletContext();

    ProductRepository productRepository = new ProductRepository();
    productRepository.save(new Product(null, "Product 1", "Description 1", new BigDecimal(100)));
    productRepository.save(new Product(null, "Product 2", "Description 2", new BigDecimal(200)));
    productRepository.save(new Product(null, "Product 3", "Description 3", new BigDecimal(300)));
    servletContext.setAttribute("productRepository", productRepository);

    CustomerRepository customerRepository = new CustomerRepository();
    customerRepository.save(new Customer(null, "Customer1", "email1@customer.ru"));
    customerRepository.save(new Customer(null, "Customer2", "email2@customer.ru"));
    customerRepository.save(new Customer(null, "Customer3", "email3@customer.ru"));
    servletContext.setAttribute("customerRepository", customerRepository);

    CategoryRepository categoryRepository = new CategoryRepository();
    categoryRepository.save(new Category(null, "Category1"));
    categoryRepository.save(new Category(null, "Category2"));
    categoryRepository.save(new Category(null, "Category3"));
    servletContext.setAttribute("categoryRepository", categoryRepository);

  }
}
