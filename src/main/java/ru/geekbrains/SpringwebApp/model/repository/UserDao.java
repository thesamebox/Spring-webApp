package ru.geekbrains.SpringwebApp.model.repository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.SpringwebApp.model.entity.Product;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDao {
    private final EntityManager entityManager;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> whoBought(long userid) {
        return entityManager.createNativeQuery(String.format("SELECT PRODUCT.id, PRODUCT.name, PRODUCT.cost from USERS_PRODUCT left join PRODUCT P on P.ID = USERS_PRODUCT.PRODUCTID where USERS_PRODUCT.userid = %s", userid), Product.class).getResultList();
    }
}
