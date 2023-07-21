package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Customer;
import ariefbelajarjava.persistence.api.entity.CustomerType;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

public class TransientTest {

    @Test
    void transientTest() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("2");
        customer.setName("Hilmi");
        customer.setPrimary_email("contoh@example.com");
        customer.setFullName("Hilmi AKbar Muharrom");
        customer.setType(CustomerType.PREMIUM);
        customer.setAge((byte) 19);
        customer.setMarried(false);

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
