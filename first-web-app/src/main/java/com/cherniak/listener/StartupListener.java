package com.cherniak.listener;

import com.cherniak.persist.Product;
import com.cherniak.persist.ProductRepository;
import java.math.BigDecimal;
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
    ProductRepository productRepository = new ProductRepository();

    productRepository.save(new Product(null, "Product 1", "Description 1", new BigDecimal(100)));
    productRepository.save(new Product(null, "Product 2", "Description 2", new BigDecimal(200)));
    productRepository.save(new Product(null, "Product 3", "Description 3", new BigDecimal(300)));

    sce.getServletContext().setAttribute("productRepository", productRepository);
  }
}
