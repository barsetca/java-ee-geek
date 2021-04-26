package com.cherniak.service.repr;

import com.cherniak.service.ProductServiceRemote;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRepr implements Serializable {

  private Long id;

  private String name;

  private String description;

  private BigDecimal price;

  private Long categoryId;

  private String categoryName;

}
