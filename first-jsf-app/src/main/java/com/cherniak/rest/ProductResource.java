package com.cherniak.rest;

import com.cherniak.service.repr.ProductRepr;
import java.util.List;
import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Local
@Path("/v1/product")
public interface ProductResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<ProductRepr> findAll();

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  ProductRepr findById(@PathParam("id") long id);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  void insert(ProductRepr productRepr);

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  void update(ProductRepr productRepr);

  @DELETE
  @Path("/{id}")
  void delete(@PathParam("id") Long id);

  @GET
  @Path("/by_name")
  @Produces(MediaType.APPLICATION_JSON)
  ProductRepr findByName(@QueryParam("name") String name);

  @GET
  @Path("/by_category_id")
  @Produces(MediaType.APPLICATION_JSON)
  List<ProductRepr> findAllByCategoryIdFetch(@QueryParam("category_id") Long categoryId);

}
