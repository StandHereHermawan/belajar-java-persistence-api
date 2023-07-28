package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Brand;
import ariefbelajarjava.persistence.api.entity.Product;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchTest {

    @Test
    void fetchLazy() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Product product = entityManager.find(Product.class, "p1");
        Brand brand = product.getBrand();
        brand.getName();
        Assertions.assertNotNull(brand);
        // Brand brand1 = entityManager.find(Brand.class, "p1");

        entityTransaction.commit();
        entityManager.close();
    }
}
