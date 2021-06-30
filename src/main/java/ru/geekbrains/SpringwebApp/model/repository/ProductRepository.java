package ru.geekbrains.SpringwebApp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.SpringwebApp.model.entity.Product;
import ru.geekbrains.SpringwebApp.model.entity.User;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("select distinct u.id, u.name from Users_products up left join User u on u.id = up.user_id where up.product_id = :id")
    @Query(value = "SELECT DISTINCT id, name from USERS_PRODUCT left join USERS U on U.ID = USERS_PRODUCT.USER_ID where product_id = :id", nativeQuery = true)
    List<User> whoBought(long id);

    @Modifying
    @Transactional
    @Query("update Product p set p.name = :name, p.cost = :cost where p.id = :id")
    void update (long id, String name, double cost);
}
