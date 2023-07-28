package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Employee;
import ariefbelajarjava.persistence.api.entity.Manager;
import ariefbelajarjava.persistence.api.entity.VicePresident;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

public class InheritanceTest {

    @Test
    void singleTableInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Employee employee = new Employee();
        employee.setId("employee-1");
        employee.setName("Arief Karditya");
        entityManager.persist(employee);

        Manager manager = new Manager();
        manager.setId("manager-1");
        manager.setName("Faris Husein");
        manager.setTotalEmployee(10);
        entityManager.persist(manager);

        VicePresident vicePresident = new VicePresident();
        vicePresident.setId("vp-1");
        vicePresident.setName("Hilmi Akbar");
        vicePresident.setTotalManager(5);
        entityManager.persist(vicePresident);

        entityTransaction.commit();
        entityManager.close();
    }
}
