package com.cherniak.persist;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

@Stateless
public class ProductRepository {

  @PersistenceContext(unitName = "ds")
  private EntityManager em;

  @Transactional
  public void save(Product product) {
    if (product.getId() == null) {
      em.persist(product);
    }
    em.merge(product);
  }

  @Transactional
  public void delete(Long id) {
    em.createNamedQuery("deleteProductById")
        .setParameter("id", id)
        .executeUpdate();
  }

  public Product findById(Long id) {
    return em.find(Product.class, id);
  }

  public List<Product> findAll() {
    return em.createNamedQuery("findAllProduct", Product.class)
        .getResultList();
  }

  public List<Product> findAllWithCategoryFetch() {
    return em.createNamedQuery("findAllWithCategoryFetch", Product.class)
        .getResultList();
  }

  public long count() {

    return em.createNamedQuery("count", Long.class).getSingleResult();
  }
}
