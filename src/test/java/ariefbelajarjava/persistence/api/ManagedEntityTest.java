package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Brand;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

public class ManagedEntityTest {

    @Test
    void managedEntityCreateUpdate() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // unmanaged entity
        Brand brand = new Brand();
        brand.setId("apple");
        brand.setName("Apple");
        entityManager.persist(brand); // managed entity

        brand.setName("Apple International"); // auto update karena sudah jadi managed entity
        // entityManager.merge(brand);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void managedEntityFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // managed entity
        Brand brand = entityManager.find(Brand.class, "apple");
        brand.setName("Apple Indonesia");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void detachManagedEntity() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // managed entity
        Brand brand = entityManager.find(Brand.class, "apple");
        entityManager.detach(brand); // unmanaged entity
        brand.setName("Apple Test");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void updateEntityAfterCommit() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // managed entity
        Brand brand = entityManager.find(Brand.class, "apple");
        entityManager.detach(brand);

        entityTransaction.commit(); // menjadi unmanaged entity dan managed entity sebelumnya dihapus
        entityManager.close(); // entity manager dihapus

        brand.setName("Apple Test");
        // Operasi tidak terlaksana karena operasi dilaksanakan setelah commit dan entity manager telah dihapus
    }
}
