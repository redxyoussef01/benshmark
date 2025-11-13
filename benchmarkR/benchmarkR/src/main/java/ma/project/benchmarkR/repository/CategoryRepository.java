package ma.project.benchmarkR.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.project.benchmarkR.model.Category;

//Pas besoin d'annotations @RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {
 // Les méthodes CRUD de base sont fournies par JpaRepository
}