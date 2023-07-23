package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Member;
import ariefbelajarjava.persistence.api.entity.Name;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CollectionTest {

    @Test
    void collection() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Name name = new Name();
        name.setFirstName("Arief");
        name.setMiddleName("Karditya");
        name.setLastName("Hermawan");

        Member member = new Member();
        member.setEmail("test@example.com");
        member.setName(name);
        member.setHobbies(new ArrayList<>());
        member.getHobbies().add("Nonton Youtube");
        member.getHobbies().add("Maen Warthunder");

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void updateOperation() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class, 2);

        member.getHobbies().add("DIY Bongkar CVT Vario");

        entityManager.merge(member);

        entityTransaction.commit();
        entityManager.close();
    }
}
