package ru.geekbrains.SpringwebApp.model.repository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.SpringwebApp.model.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class ProductDao {
    private final EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Product product) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(product);
        transaction.commit();
    }

    public List<Product> findAll() {

        entityManager.createNativeQuery("CREATE TABLE 'PRODUCT.product' IF NOT EXIST  ('id' long auto_increment primary key, 'name' varchar(255), 'cost' double");

        return entityManager.createNativeQuery("select * from product", Product.class)
                .getResultList();
    }

    public Product findById(long id) {
        return entityManager.find(Product.class, id);
    }
}
