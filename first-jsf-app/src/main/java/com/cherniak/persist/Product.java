package com.cherniak.persist;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "products")
@NamedQueries({
    @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id = :id"),
    @NamedQuery(name = "findAllProduct", query = "select p from Product p"),
    @NamedQuery(name = "count", query = "select count(p) from Product p"),
    @NamedQuery(name = "findAllWithCategoryFetch", query = "select p from Product p left join fetch p.category")
})
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private String description;

  @Column
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

}
