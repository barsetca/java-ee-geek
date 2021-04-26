package com.cherniak.persist;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

@Stateless
public class CategoryRepository {

  @PersistenceContext(unitName = "ds")
  private EntityManager em;

  @TransactionAttribute
  public void save(Category category) {
    if (category.getId() == null) {
      em.persist(category);
    }
    em.merge(category);
  }

  @TransactionAttribute
  public void delete(Long id) {
    em.createNamedQuery("deleteCategoryById")
        .setParameter("id", id)
        .executeUpdate();
  }

  public Category findById(Long id) {
    return em.find(Category.class, id);
  }

  public List<Category> findAll() {
    return em.createNamedQuery("findAllCategory", Category.class)
        .getResultList();
  }

  public Long countAll() {
    return em.createNamedQuery("countCategories", Long.class)
        .getSingleResult();
  }

  public Category getReference(Long id) {
    return em.getReference(Category.class, id);
  }

}
