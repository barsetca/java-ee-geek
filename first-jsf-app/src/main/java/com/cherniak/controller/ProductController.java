package com.cherniak.controller;

import com.cherniak.persist.Product;
import com.cherniak.persist.ProductRepository;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class ProductController implements Serializable {

  @Inject
  private ProductRepository productRepository;

  private Product product;

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public String editProduct(Product product) {
    this.product = product;
    return "/product_form.xhtml?faces-redirect=true";
  }

  public void deleteProduct(Product product) {
    productRepository.delete(product.getId());
  }

  public String saveProduct() {
    productRepository.save(product);
    return "/product.xhtml?faces-redirect=true";
  }

  public String addProduct() {
    this.product = new Product();
    return "/product_form.xhtml?faces-redirect=true";
  }
}
