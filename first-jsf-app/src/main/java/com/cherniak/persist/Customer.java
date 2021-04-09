package com.cherniak.persist;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
@NamedQueries({
    @NamedQuery(name = "deleteCustomerById", query = "delete from Customer c where c.id = :id"),
    @NamedQuery(name = "findAllCustomer", query = "select c from Customer c"),
    @NamedQuery(name = "countCustomers", query = "select count(c) from Customer c")
})
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private String email;

}
