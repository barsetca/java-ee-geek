package com.cherniak.service;

import com.cherniak.persist.Category;
import com.cherniak.persist.CategoryRepository;
import com.cherniak.persist.dto.CategoryDto;
import com.cherniak.rest.CategoryResource;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;

@Stateful
public class CategoryServiceImpl implements CategoryResource {

  @EJB
  private CategoryRepository categoryRepository;

  @Override
  public void insert(CategoryDto categoryDto) {
    if (categoryDto.getId() != null) {
      throw new IllegalArgumentException("Not null id in the inserted Category");
    }
    save(categoryDto);
  }

  @TransactionAttribute
  public void save(CategoryDto categoryDto) {
    categoryRepository.save(new Category(categoryDto.getId(), categoryDto.getName()));
  }

  @Override
  public List<CategoryDto> findAll() {
    return categoryRepository.findAll().stream().map(c -> new CategoryDto(c.getId(), c.getName()))
        .collect(
            Collectors.toList());
  }
}
