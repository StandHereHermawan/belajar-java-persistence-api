package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Brand;
import ariefbelajarjava.persistence.api.entity.Member;
import ariefbelajarjava.persistence.api.entity.Product;
import ariefbelajarjava.persistence.api.entity.User;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JpaQueryLanguageTest {

    @Test
    void select() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b", Brand.class);
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void whereClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("select m from Member m where " +
                "m.name.firstName = :firstName and m.name.lastName =:lastName", Member.class);
        query.setParameter("firstName", "Hilmi");
        query.setParameter("lastName", "Muharrom");

        List<Member> list = query.getResultList();
        for (Member member : list) {
            System.out.println(member.getId() + " : " + member.getFullName());
        }

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void joinClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p join p.brand b where b.name = :brand", Product.class);
        query.setParameter("brand", "Byu provider Digital");

        List<Product> products = query.getResultList();
        for (Product product : products) {
            System.out.println(product.getId() + " : " + product.getName() + " : " + product.getBrand().getName());
        }

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void joinFetchClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<User> query = entityManager.createQuery("select u from User u join fetch u.likes p where p.name = :product", User.class);
        query.setParameter("product", "Kuota 75GB");

        List<User> users = query.getResultList();
        for (User user : users) {
            System.out.println("User: " + user.getName());
            for (Product product : user.getLikes()) {
                System.out.println("Product: " + product.getName());
            }
        }

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void orderClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.name desc", Brand.class);
        List<Brand> brands = query.getResultList();
        for (Brand brand : brands) {
            System.out.println(brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void insertRandomBrand() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        for (int i = 0; i < 10; i++) {
            Brand brand = new Brand();
            brand.setId(String.valueOf(i));
            brand.setName("Brand" + i);
            entityManager.persist(brand);
        }

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void limitOffsetMaxResult() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.id", Brand.class);
        query.setMaxResults(8);

        List<Brand> brands = query.getResultList();
        for (Brand brand : brands) {
            System.out.println(brand.getId()+" "+brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void limitOffsetInitialResult() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.id", Brand.class);
        query.setFirstResult(10); // skipped first 10 results
        query.setMaxResults(5); // only shows 5 data after skipped the first results

        List<Brand> brands = query.getResultList();
        for (Brand brand : brands) {
            System.out.println(brand.getId()+" "+brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void namedQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createNamedQuery("Brand.findAllByName", Brand.class);
        query.setParameter("name", "Xiaomi");

        List<Brand> brands = query.getResultList();
        for (Brand brand : brands) {
            System.out.println(brand.getId() + " " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void selectSomeFieldsUseObjectArrayParentClass() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select b.id, b.name from Brand b where b.name = :name", Object[].class);
        query.setParameter("name", "Xiaomi");

        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects) {
            System.out.println(object[0] + " : " + object[1]);
        }

        entityTransaction.commit();
        entityManager.close();
    }
}
