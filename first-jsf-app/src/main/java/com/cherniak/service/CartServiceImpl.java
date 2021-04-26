package com.cherniak.service;

import com.cherniak.service.repr.ProductRepr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.ejb.Stateful;

@Stateful
public class CartServiceImpl implements CartService {

  private final Map<Long, ProductRepr> productMap = new ConcurrentHashMap<>();

  @Override
  public void add(ProductRepr productRepr) {
    productMap.put(productRepr.getId(), productRepr);
  }

  @Override
  public void remove(long id) {
    productMap.remove(id);
  }

  @Override
  public List<ProductRepr> findAll() {
     return new ArrayList<>(productMap.values());
  }
}
