package ru.geekbrains.SpringwebApp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.SpringwebApp.model.entity.Product;
import ru.geekbrains.SpringwebApp.model.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    //todo No converter found capable of converting from type [java.math.BigInteger] to type [ru.geekbrains.SpringwebApp.model.entity.Product]
    @Query(value = "SELECT P.id, P.name, UP.ex_actual_cost " +
            "from USERS_PRODUCTS UP " +
            "left join PRODUCT P " +
            "on P.id = UP.product_id " +
            "where UP.user_id = :id",
            nativeQuery = true)
    List<Product> whatBought(long id);

}
