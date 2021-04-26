package com.cherniak.service;

import com.cherniak.service.repr.ProductRepr;
import java.util.List;

public interface ProductServiceRemote {

  List<ProductRepr> findAllRemote();

}
