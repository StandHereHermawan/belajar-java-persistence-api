package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Credential;
import ariefbelajarjava.persistence.api.entity.User;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityRelationshipTest {

    @Test
    void oneToOnePersist() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Credential credential = new Credential();
        credential.setId("Orang_Bogor");
        credential.setEmail("HilmiAkbar@example.com");
        credential.setPassword("rahasia");

        entityManager.persist(credential);

        User user = new User();
        user.setId("Orang_Bogor");
        user.setName("Hilmi Akbar");

        entityManager.persist(user);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToOneFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, "Orang_Bogor");
        Assertions.assertNotNull(user.getCredential());

        Assertions.assertEquals("HilmiAkbar@example.com",user.getCredential().getEmail());
        Assertions.assertEquals("rahasia",user.getCredential().getPassword());

        entityTransaction.commit();
        entityManager.close();
    }
}
