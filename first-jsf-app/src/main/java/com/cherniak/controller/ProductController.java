package com.cherniak.controller;

import com.cherniak.persist.Category;
import com.cherniak.persist.CategoryRepository;
import com.cherniak.service.ProductService;
import com.cherniak.service.repr.ProductRepr;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

@SessionScoped
@Named
public class ProductController implements Serializable {

  @EJB
  private CategoryRepository categoryRepository;

  @EJB
  private ProductService productService;

  private ProductRepr product;

  private List<ProductRepr> productList;

  private List<Category> categoryList;

  public void preloadData(ComponentSystemEvent componentSystemEvent) {
    this.productList = productService.findAllWithCategoryFetch();
    this.categoryList = categoryRepository.findAll();
  }

  public ProductRepr getProduct() {
    return product;
  }

  public void setProduct(ProductRepr product) {
    this.product = product;
  }

  public List<ProductRepr> findAll() {
    return productList;
  }

  public String editProduct(ProductRepr product) {
    this.product = product;
    return "/product_form.xhtml?faces-redirect=true";
  }

  public void deleteProduct(ProductRepr product) {
    productService.delete(product.getId());
  }

  public String saveProduct() {
    productService.save(product);
    return "/product.xhtml?faces-redirect=true";
  }

  public String addProduct() {
    this.product = new ProductRepr();
    return "/product_form.xhtml?faces-redirect=true";
  }

  public List<Category> getCategories() {
    return categoryList;
  }
}
