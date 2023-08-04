package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Brand;
import ariefbelajarjava.persistence.api.entity.SimpleBrand;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CriteriaTest {

    @Test
    void criteriaQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);
        Root<Brand> b = criteriaQuery.from(Brand.class);
        criteriaQuery.select(b); // select b from Brand b

        TypedQuery<Brand> brandTypedQuery = entityManager.createQuery(criteriaQuery);
        List<Brand> brands = brandTypedQuery.getResultList();
        for (Brand brand : brands) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaQueryNonEntity() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Brand> b = criteriaQuery.from(Brand.class);
        criteriaQuery.select(criteriaBuilder.array(b.get("id"),b.get("name")));
        // select b.id, b.name from Brand b

        TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);
        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects) {
            System.out.println("Id : "+object[0]);
            System.out.println("Name : "+object[1]);
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaQueryConstructorExpression() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<SimpleBrand> criteriaQuery = criteriaBuilder.createQuery(SimpleBrand.class);
        Root<Brand> b = criteriaQuery.from(Brand.class);
        criteriaQuery.select(criteriaBuilder.construct(SimpleBrand.class,b.get("id"),b.get("name")));

        TypedQuery<SimpleBrand> query = entityManager.createQuery(criteriaQuery);
        List<SimpleBrand> simpleBrands = query.getResultList();
        for (SimpleBrand simpleBrand : simpleBrands) {
            System.out.println(simpleBrand.getId()+" : "+simpleBrand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }
}
