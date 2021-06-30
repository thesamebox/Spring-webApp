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
        //fixed
        return entityManager.createNativeQuery(String.format("SELECT P.id, P.name, UP.ex_actual_cost from USERS_PRODUCT UP left join PRODUCT P on P.ID = UP.PRODUCTID where UP.userid = %s", userid), Product.class).getResultList();
    }
}
