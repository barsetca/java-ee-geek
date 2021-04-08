package com.cherniak.controller;

import com.cherniak.persist.Customer;
import com.cherniak.persist.CustomerRepository;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class CustomerController implements Serializable {

  @Inject
  private CustomerRepository customerRepository;

  private Customer customer;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  public String editCustomer(Customer customer) {
    this.customer = customer;
    return "/customer_form.xhtml?faces-redirect=true";
  }

  public void deleteCustomer(Customer customer) {
    customerRepository.delete(customer.getId());
  }

  public String saveCustomer() {
    customerRepository.save(customer);
    return "/customer.xhtml?faces-redirect=true";
  }

  public String addCustomer() {
    this.customer = new Customer();
    return "/customer_form.xhtml?faces-redirect=true";
  }
}
