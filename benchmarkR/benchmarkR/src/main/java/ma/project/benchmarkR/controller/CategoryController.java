package ma.project.benchmarkR.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ma.project.benchmarkR.model.Category;
import ma.project.benchmarkR.model.Item;
import ma.project.benchmarkR.repository.CategoryRepository;
import ma.project.benchmarkR.repository.ItemRepository;

import java.net.URI;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    // GET /categories?page=&size=
    @GetMapping
    public Page<Category> getCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    // GET /categories/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /categories
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryRepository.save(category);
        return ResponseEntity.created(URI.create("/api/categories/" + savedCategory.getId()))
                .body(savedCategory);
    }

    // PUT /categories/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setCode(categoryDetails.getCode());
                    existingCategory.setName(categoryDetails.getName());
                    Category updatedCategory = categoryRepository.save(existingCategory);
                    return ResponseEntity.ok(updatedCategory);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /categories/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // GET /categories/{id}/items?page=&size=
    // CHANGEMENT ICI : Renommer le Path Variable dans l'URL et dans la méthode
    @GetMapping("/{categoryId}/items") 
    public Page<Item> getItemsByCategoryId(@PathVariable Long categoryId, Pageable pageable) {
        // Utilise la méthode du ItemRepository pour le filtrage
        return itemRepository.findByCategoryId(categoryId, pageable);
    }
}