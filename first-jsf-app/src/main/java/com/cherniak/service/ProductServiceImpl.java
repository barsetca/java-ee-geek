package com.cherniak.service;

import com.cherniak.persist.CategoryRepository;
import com.cherniak.persist.Product;
import com.cherniak.persist.ProductRepository;
import com.cherniak.rest.ProductResource;
import com.cherniak.service.repr.ProductRepr;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

@Stateless
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductResource {

  @EJB
  private ProductRepository productRepository;

  @EJB
  private CategoryRepository categoryRepository;

  @Override
  @TransactionAttribute
  public void save(ProductRepr productRepr) {
    productRepository.save(new Product(productRepr.getId(),
        productRepr.getName(),
        productRepr.getDescription(),
        productRepr.getPrice(),
        categoryRepository.getReference(productRepr.getCategoryId())
    ));
  }

  @Override
  @TransactionAttribute
  public void delete(Long id) {
    productRepository.delete(id);
  }

  @Override
  public void insert(ProductRepr productRepr) {
    if (productRepr.getId() != null) {
      throw new IllegalArgumentException("Not null id in the inserted Product");
    }
    save(productRepr);
  }

  @Override
  public void update(ProductRepr productRepr) {
    if (productRepr.getId() == null) {
      throw new IllegalArgumentException("Null id in the inserted Product");
    }
    save(productRepr);
  }

  @Override
  public ProductRepr findById(long id) {
    return createProductReprWithCategory(productRepository.findById(id));
  }

  @Override
  public List<ProductRepr> findAll() {
    return productRepository.findAll().stream()
        .map(ProductServiceImpl::createProductRepr)
        .collect(Collectors.toList());
  }

  @Override
  public List<ProductRepr> findAllWithCategoryFetch() {
    return productRepository.findAllWithCategoryFetch().stream()
        .map(ProductServiceImpl::createProductReprWithCategory)
        .collect(Collectors.toList());
  }

  @Override
  public long count() {
    return productRepository.count();
  }

  private static ProductRepr createProductReprWithCategory(Product product) {
    return new ProductRepr(product.getId(),
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        product.getCategory() != null ? product.getCategory().getId() : null,
        product.getCategory() != null ? product.getCategory().getName() : null);
  }

  private static ProductRepr createProductRepr(Product product) {
    return new ProductRepr(product.getId(),
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        null,
        null);
  }

  @Override
  public List<ProductRepr> findAllRemote() {
    return findAllWithCategoryFetch();
  }
}
