package ma.ws.jaxrs.benchmarkAa.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import ma.ws.jaxrs.benchmarkAa.entities.Item;
import ma.ws.jaxrs.benchmarkAa.entities.Category;
import ma.ws.jaxrs.benchmarkAa.dao.ItemDAO;
import ma.ws.jaxrs.benchmarkAa.dao.CategoryDAO;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {

    private final ItemDAO itemDAO = new ItemDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();

    @GET
    public List<Item> getAllItems(@QueryParam("categoryId") Long categoryId) {
        if (categoryId != null) {
            return itemDAO.findByCategoryId(categoryId);
        }
        return itemDAO.findAll();
    }

    @GET
    @Path("/{id}")
    public Item getItemById(@PathParam("id") Long id) {
        Item item = itemDAO.find(id);
        if (item == null) throw new NotFoundException("Item not found with id " + id);
        return item;
    }

    @POST
    public void addItem(Item item) {
        if (item.getCategory() != null && item.getCategory().getId() != null) {
            Category category = categoryDAO.find(item.getCategory().getId());
            if (category == null)
                throw new NotFoundException("Category not found with id " + item.getCategory().getId());
            item.setCategory(category);
        }
        itemDAO.create(item);
    }

    @PUT
    @Path("/{id}")
    public void updateItem(@PathParam("id") Long id, Item item) {
        Item existing = itemDAO.find(id);
        if (existing == null)
            throw new NotFoundException("Item not found with id " + id);

        existing.setName(item.getName());
        existing.setSku(item.getSku());
        existing.setPrice(item.getPrice());
        existing.setStock(item.getStock());

        if (item.getCategory() != null && item.getCategory().getId() != null) {
            Category category = categoryDAO.find(item.getCategory().getId());
            if (category == null)
                throw new NotFoundException("Category not found with id " + item.getCategory().getId());
            existing.setCategory(category);
        }

        itemDAO.update(existing);
    }

    @DELETE
    @Path("/{id}")
    public void deleteItem(@PathParam("id") Long id) {
        Item item = itemDAO.find(id);
        if (item == null) throw new NotFoundException("Item not found with id " + id);
        itemDAO.delete(id);
    }
}
