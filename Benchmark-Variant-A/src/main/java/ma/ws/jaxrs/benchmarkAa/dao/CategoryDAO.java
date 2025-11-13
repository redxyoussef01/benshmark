package ma.ws.jaxrs.benchmarkAa.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext; // 1. IMPORT THIS
import ma.ws.jaxrs.benchmarkAa.entities.Category;
import org.springframework.stereotype.Repository; // 2. IMPORT THIS
import org.springframework.transaction.annotation.Transactional; // 3. IMPORT THIS

@Repository // 4. Add this to make it a Spring Bean
public class CategoryDAO {

    // 5. Inject the EntityManager automatically
    @PersistenceContext
    private EntityManager em;

    // 6. Remove the JPAUtil line:
    // private EntityManager em = JPAUtil.getEntityManager(); // <-- DELETE THIS

    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }
    
    public Category find(Long id) { return em.find(Category.class, id); }
    
    @Transactional // 7. Add this - Spring will manage the transaction
    public void create(Category c) {
        // em.getTransaction().begin(); // <-- DELETE THIS
        em.persist(c);
        // em.getTransaction().commit(); // <-- DELETE THIS
    }
    
    @Transactional // 8. Add this
    public void update(Category c) {
        // em.getTransaction().begin(); // <-- DELETE THIS
        em.merge(c);
        // em.getTransaction().commit(); // <-- DELETE THIS
    }
    
    @Transactional // 9. Add this
    public void delete(Long id) {
        // em.getTransaction().begin(); // <-- DELETE THIS
        Category c = em.find(Category.class, id);
        if (c != null) em.remove(c);
        // em.getTransaction().commit(); // <-- DELETE THIS
    }
}