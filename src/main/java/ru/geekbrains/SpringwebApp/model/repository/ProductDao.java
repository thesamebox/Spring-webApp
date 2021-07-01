package ru.geekbrains.SpringwebApp.model.repository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.SpringwebApp.model.entity.Product;
import ru.geekbrains.SpringwebApp.model.entity.User;

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
        return entityManager.createNativeQuery("select * from product", Product.class)
                .getResultList();
    }

    public Product findById(long id) {
        return entityManager.find(Product.class, id);
    }

    public List<User> whoBought(long id) {
        return entityManager.createNativeQuery(String.format("SELECT DISTINCT id, name from USERS_PRODUCT left join USERS U on U.ID = USERS_PRODUCT.USERID where productid = %s", id), User.class).getResultList();
    }

    public void update (long id, String name, double cost) {
        Product p = findById(id);
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        p.setName(name);
        p.setCost(cost);
        transaction.commit();
    }

    public void delete(long id) {
        Product p = findById(id);
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(p);
        transaction.commit();
    }
}
