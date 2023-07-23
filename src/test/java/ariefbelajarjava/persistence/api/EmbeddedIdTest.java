package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Department;
import ariefbelajarjava.persistence.api.entity.DepartmentId;
import ariefbelajarjava.persistence.api.entity.Member;
import ariefbelajarjava.persistence.api.entity.Name;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmbeddedIdTest {

    @Test
    void embeddedId() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartmentId id = new DepartmentId();
        id.setCompanyId("Belum ada Company");
        id.setDepartmentId("Belum ada");

        Department department = new Department();
        department.setId(id);
        department.setName("Belum ada Department");

        entityManager.persist(department);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void embeddedIdFindAliasReadOperation() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartmentId id = new DepartmentId();
        id.setCompanyId("Belum ada Company");
        id.setDepartmentId("Belum ada");

        Department department = entityManager.find(Department.class, id);
        Assertions.assertEquals("Belum ada Department",department.getName());

        entityTransaction.commit();
        entityManager.close();
    }
}
