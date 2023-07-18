package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Category;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedValueTest {

    @Test
    void generatedValue() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Category category = new Category();
        category.setName("Gadget");
        category.setDescription("Produk Gadget");

        entityManager.persist(category);
        Assertions.assertNotNull(category.getId());

        entityTransaction.commit();
        entityManager.close();
    }
}
