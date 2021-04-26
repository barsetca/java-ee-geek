package com.cherniak.service;

import com.cherniak.service.repr.ProductRepr;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CartService {

  void add(ProductRepr productRepr);

  void remove(long id);

  List<ProductRepr> findAll();
}
