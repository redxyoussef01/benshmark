package ma.ws.jaxrs.benchmarkAa.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.ws.jaxrs.benchmarkAa.entities.Category;
import ma.ws.jaxrs.benchmarkAa.dao.CategoryDAO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; // <-- IMPORT THIS
import org.springframework.stereotype.Component;

@Component
@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryController {

    // 1. DO NOT use new() here
    private final CategoryDAO categoryDAO;

    // 2. Add this constructor to let Spring inject the DAO
    @Autowired
    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    // --- All other methods are now correct ---

    @GET
    public List<Category> getAll() { return categoryDAO.findAll(); }

    @GET @Path("/{id}")
    public Category getById(@PathParam("id") Long id) {
        Category c = categoryDAO.find(id);
        if (c == null) throw new NotFoundException("Category not found");
        return c;
    }

    @POST
    public void add(Category c) { categoryDAO.create(c); }

    @PUT @Path("/{id}")
    public void update(@PathParam("id") Long id, Category c) {
        c.setId(id);
        categoryDAO.update(c);
    }

    @DELETE @Path("/{id}")
    public void delete(@PathParam("id") Long id) { categoryDAO.delete(id); }
}