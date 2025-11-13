package ma.project.benchmarkD.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import ma.project.benchmarkD.model.Item;

@RepositoryRestResource(path = "items", collectionResourceRel = "items")
public interface ItemRepository extends JpaRepository<Item, Long> {

    // This method automatically creates the filter endpoint:
    // GET /api/items/search/findByCategoryId?categoryId=...	
    // This satisfies requirement 
    @RestResource(path = "findByCategoryId", rel = "findByCategoryId")
    Page<Item> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    // All other endpoints (GET, POST, PUT, DELETE) 
    // are created automatically[cite: 61, 63, 67, 68, 69].
}