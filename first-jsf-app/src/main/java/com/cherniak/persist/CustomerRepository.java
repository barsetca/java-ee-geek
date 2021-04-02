package com.cherniak.persist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named
public class CustomerRepository {
  private final Map<Long, Customer> customerMap = new ConcurrentHashMap<>();

  private final AtomicLong identity = new AtomicLong(0);

  @PostConstruct
  public void init() {
    save(new Customer(null, "Customer1", "email1@customer.ru"));
    save(new Customer(null, "Customer2", "email2@customer.ru"));
    save(new Customer(null, "Customer3", "email3@customer.ru"));

  }

  public void save(Customer customer) {
    if (customer.getId() == null) {
      customer.setId(identity.incrementAndGet());
    }
    customerMap.put(customer.getId(), customer);
  }

  public void delete(Long id) {
    customerMap.remove(id);
  }

  public Customer findById(Long id) {
    return customerMap.get(id);
  }

  public List<Customer> findAll() {
    return new ArrayList<>(customerMap.values());
  }
}
