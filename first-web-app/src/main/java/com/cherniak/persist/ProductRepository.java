package com.cherniak.persist;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {

  private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
  private final AtomicLong identity = new AtomicLong(0);

  public void save(Product product) {
    if (product.getId() == null) {
      product.setId(identity.incrementAndGet());
    }
    productMap.put(product.getId(), product);
  }

  public void deleteById(Long id) {
    productMap.remove(id);
  }

  public Product findById(long id) {
    return productMap.get(id);
  }

  public List<Product> findAll() {
    return List.copyOf(productMap.values());
  }

}
