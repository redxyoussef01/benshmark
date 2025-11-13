package ma.project.benchmarkD.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.project.benchmarkD.model.Category;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ma.project.benchmarkD.model.Category;

// This annotation exposes the repository at the path "/api/categories"
@RepositoryRestResource(path = "categories", collectionResourceRel = "categories")
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    // NO CODE NEEDED HERE.
    // Spring Data REST automatically creates:
    // GET /api/categories [cite: 54]
    // GET /api/categories/{id} [cite: 55]
    // POST /api/categories [cite: 56]
    // PUT /api/categories/{id} [cite: 57]
    // DELETE /api/categories/{id} [cite: 59]
    //
    // It also automatically creates the relationship endpoint:
    // GET /api/categories/{id}/items [cite: 72]
}