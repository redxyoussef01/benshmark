package ma.project.benchmarkR.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ma.project.benchmarkR.model.Item;
import ma.project.benchmarkR.repository.ItemRepository;

import java.net.URI;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    // GET /items?page=&size= [cite: 61]
    // ET GET /items?categoryId=...&page=&size= [cite: 66]
    @GetMapping
    public Page<Item> getItems(
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            Pageable pageable) {
        
        if (categoryId != null) {
            // Gère le filtrage relationnel [cite: 66]
            return itemRepository.findByCategoryId(categoryId, pageable);
        } else {
            // Gère la liste paginée simple [cite: 61]
            return itemRepository.findAll(pageable);
        }
    }

    // GET /items/{id} [cite: 63]
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return itemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /items [cite: 67]
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        // Note: La logique pour lier la catégorie doit être gérée
        // (par exemple, s'attendre à un `category.id` dans le JSON)
        Item savedItem = itemRepository.save(item);
        return ResponseEntity.created(URI.create("/api/items/" + savedItem.getId()))
                .body(savedItem);
    }

    // PUT /items/{id} [cite: 68]
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        return itemRepository.findById(id)
                .map(existingItem -> {
                    existingItem.setName(itemDetails.getName());
                    existingItem.setSku(itemDetails.getSku());
                    existingItem.setPrice(itemDetails.getPrice());
                    existingItem.setStock(itemDetails.getStock());
                    existingItem.setCategory(itemDetails.getCategory());
                    // updatedAt sera géré par @UpdateTimestamp
                    Item updatedItem = itemRepository.save(existingItem);
                    return ResponseEntity.ok(updatedItem);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /items/{id} [cite: 69]
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        if (!itemRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}