package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.*;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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

    @Test
    void singleTableFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Manager manager = entityManager.find(Manager.class, "manager-1");
        Assertions.assertEquals("Faris Husein", manager.getName());

        Employee employee = entityManager.find(Employee.class, "vp-1");
        VicePresident vicePresident = (VicePresident) employee;
        Assertions.assertEquals("Hilmi Akbar", vicePresident.getName());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinedTableInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        PaymentGopay gopay = new PaymentGopay();
        gopay.setId("gopay1");
        gopay.setAmount(100_000L);
        gopay.setGopayId("089999999999");
        entityManager.persist(gopay);

        PaymentCreditCard creditCard = new PaymentCreditCard();
        creditCard.setId("cc1");
        creditCard.setAmount(500_000L);
        creditCard.setMaskedCard("4555-5555");
        creditCard.setBank("BCA");
        entityManager.persist(creditCard);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinedTableFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        PaymentGopay gopay = entityManager.find(PaymentGopay.class, "gopay1");
        PaymentCreditCard creditCard = entityManager.find(PaymentCreditCard.class, "cc1");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinedTableFindParent() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Payment gopay1 = entityManager.find(Payment.class, "gopay1");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void tablePerClassInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Transaction transaction1 = new Transaction();
        transaction1.setId("t1");
        transaction1.setCreatedAt(LocalDateTime.now());
        transaction1.setBalance(1_000_000L);
        entityManager.persist(transaction1);

        TransactionDebit debitTransaction = new TransactionDebit();
        debitTransaction.setId("t2");
        debitTransaction.setCreatedAt(LocalDateTime.now());
        debitTransaction.setBalance(2_000_000L);
        debitTransaction.setDebitAmount(1_000_000L);
        entityManager.persist(debitTransaction);

        TransactionCredit creditTransaction = new TransactionCredit();
        creditTransaction.setId("t3");
        creditTransaction.setCreatedAt(LocalDateTime.now());
        creditTransaction.setBalance(1_000_000L);
        creditTransaction.setCreditAmount(1_000_000L);
        entityManager.persist(creditTransaction);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void tablePerClassFindByChild() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TransactionDebit transactionDebit = entityManager.find(TransactionDebit.class, "t2");
        TransactionCredit transactionCredit = entityManager.find(TransactionCredit.class, "t3");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void tablePerClassFindByParent() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Transaction transaction = entityManager.find(Transaction.class, "t1");

        entityTransaction.commit();
        entityManager.close();
    }
}
