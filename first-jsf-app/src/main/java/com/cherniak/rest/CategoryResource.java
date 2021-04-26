package com.cherniak.rest;

import com.cherniak.persist.dto.CategoryDto;
import java.util.List;
import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Local
@Path("/v1/category")
public interface CategoryResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<CategoryDto> findAll();

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  void insert(CategoryDto categoryDto);

}
