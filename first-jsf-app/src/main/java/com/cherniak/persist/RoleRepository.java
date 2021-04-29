package com.cherniak.persist;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RoleRepository {

  @PersistenceContext(unitName = "ds")
  protected EntityManager em;

  @TransactionAttribute
  public Role merge(Role role) {
    return em.merge(role);
  }

  public Role findById(int id) {
    return em.find(Role.class, id);
  }

  public List<Role> getAllRoles() {
    return em.createQuery("select r from Role r", Role.class).getResultList();
  }

  public long getCount() {
    return em.createQuery("select count(r) from Role r", Long.class)
        .getSingleResult();
  }
}
