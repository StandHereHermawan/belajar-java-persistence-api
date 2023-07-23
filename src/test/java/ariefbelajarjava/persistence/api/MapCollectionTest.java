package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Member;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class MapCollectionTest {

    @Test
    void updateOperationToSkillsTable() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class, 2);
        member.setSkills(new HashMap<>());
        member.getSkills().put("Warthunder Air RB Yak-38",81);
        member.getSkills().put("Warthunder Ground RB ZTZ59D1",78);
        member.getSkills().put("Bongkar CVT Vario",79);

        entityManager.merge(member);

        entityTransaction.commit();
        entityManager.close();
    }
}
