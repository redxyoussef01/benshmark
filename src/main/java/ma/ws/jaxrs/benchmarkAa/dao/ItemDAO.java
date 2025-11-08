package ma.ws.jaxrs.benchmarkAa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import ma.ws.jaxrs.benchmarkAa.entities.Item;
import ma.ws.jaxrs.benchmarkAa.util.JPAUtil;

public class ItemDAO {

    private final EntityManager em = JPAUtil.getEntityManager();

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

    public void create(Item item) {
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
    }

    public void update(Item item) {
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        Item existing = em.find(Item.class, id);
        if (existing != null) em.remove(existing);
        em.getTransaction().commit();
    }
}
