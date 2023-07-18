package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Customer;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

public class DataTypeTest {

    @Test
    void dataType() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setName("Hilmi");
        customer.setId("2");
        customer.setAge((byte)30);
        customer.setMarried(false);

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
