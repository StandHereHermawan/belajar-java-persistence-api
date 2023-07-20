package ariefbelajarjava.persistence.api;

import ariefbelajarjava.persistence.api.entity.Image;
import ariefbelajarjava.persistence.api.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LargeObjectTest {

    @Test
    void largeObject() throws IOException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Image image = new Image();
        image.setName("Contoh Image");
        image.setDescription("Contoh deskripsi Image");

        byte[] bytes = Files.readAllBytes(Path.of(getClass().getResource("/images/troll.png").getPath()));

        image.setImage(bytes);

        entityManager.persist(image);

        entityTransaction.commit();
        entityManager.close();
    }
}
