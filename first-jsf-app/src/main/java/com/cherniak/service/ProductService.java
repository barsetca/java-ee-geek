package com.cherniak.service;

import com.cherniak.service.repr.ProductRepr;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProductService {

  void save(ProductRepr product);

  void delete(Long id);

  ProductRepr findById(long id);

  List<ProductRepr> findAll();

  List<ProductRepr> findAllWithCategoryFetch();

  long count();

}
