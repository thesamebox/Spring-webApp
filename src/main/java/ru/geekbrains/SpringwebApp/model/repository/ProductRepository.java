package ru.geekbrains.SpringwebApp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.SpringwebApp.model.entity.Product;
import ru.geekbrains.SpringwebApp.model.entity.User;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //todo No converter found capable of converting from type [java.math.BigInteger] to type [ru.geekbrains.SpringwebApp.model.entity.User]
    @Query(value = "SELECT DISTINCT U.id, U.name " +
            "from USERS_PRODUCTS UP " +
            "left join USER U " +
            "on U.id = UP.user_id " +
            "where product_id = :id",
            nativeQuery = true)
    List<User> whoBought(long id);

    @Modifying
    @Transactional
    @Query("update Product p set p.name = :name, p.cost = :cost where p.id = :id")
    void update (long id, String name, double cost);
}
