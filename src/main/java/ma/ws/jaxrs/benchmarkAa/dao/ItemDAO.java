package ma.ws.jaxrs.benchmarkAa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext; // 1. IMPORT THIS
import jakarta.persistence.TypedQuery;
import java.util.List;
import ma.ws.jaxrs.benchmarkAa.entities.Item;
import org.springframework.stereotype.Repository; // 2. IMPORT THIS
import org.springframework.transaction.annotation.Transactional; // 3. IMPORT THIS

@Repository // 4. Add this to make it a Spring Bean
public class ItemDAO {

    @PersistenceContext // 5. Add this to inject the EntityManager
    private EntityManager em;

    // 6. Remove the JPAUtil line:
    // private final EntityManager em = JPAUtil.getEntityManager(); // <-- DELETE THIS

    public List<Item> findAll() {
        return em.createQuery("SELECT i FROM Item i", Item.class).getResultList();
    }

    public Item find(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findByCategoryId(Long categoryId) {
        TypedQuery<Item> q = em.createQuery(
                "SELECT i FROM Item i WHERE i.category.id = :categoryId", Item.class);
        q.setParameter("categoryId", categoryId);
        return q.getResultList();
    }

    @Transactional // 7. Add this - Spring will manage the transaction
    public void create(Item item) {
        // em.getTransaction().begin(); // <-- DELETE THIS
        em.persist(item);
        // em.getTransaction().commit(); // <-- DELETE THIS
    }

    @Transactional // 8. Add this
    public void update(Item item) {
        // em.getTransaction().begin(); // <-- DELETE THIS
        em.merge(item);
        // em.getTransaction().commit(); // <-- DELETE THIS
    }

    @Transactional // 9. Add this
    public void delete(Long id) {
        // em.getTransaction().begin(); // <-- DELETE THIS
        Item existing = em.find(Item.class, id);
        if (existing != null) em.remove(existing);
        // em.getTransaction().commit(); // <-- DELETE THIS
    }
}