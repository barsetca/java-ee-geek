package com.cherniak.controller;

import com.cherniak.service.CartService;
import com.cherniak.service.repr.ProductRepr;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class CartController implements Serializable {

  @EJB
  private CartService cartService;

  public void add(ProductRepr product) {
    cartService.add(product);
  }

  public void remove(ProductRepr product) {
    cartService.remove(product.getId());
  }

  public List<ProductRepr> findAll() {
    return cartService.findAll();
  }

}
