package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Image;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class LargeObjectTest {

    @Test
    void largeObject() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Image image = new Image();
        image.setName("Contoh Image");
        image.setDescription("Contoh deskripsi Image");

        try (InputStream inputStream = getClass().getResourceAsStream("/images/troll.png")) {
            image.setImage(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        entityManager.persist(image);

        entityTransaction.commit();
        entityManager.close();
    }
}
