package com.cherniak.service;

import com.cherniak.persist.CategoryRepository;
import com.cherniak.persist.Product;
import com.cherniak.persist.ProductRepository;
import com.cherniak.service.repr.ProductRepr;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

@Stateless
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote {

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
