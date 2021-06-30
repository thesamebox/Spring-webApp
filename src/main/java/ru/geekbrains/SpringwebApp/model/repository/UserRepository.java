package ru.geekbrains.SpringwebApp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.SpringwebApp.model.entity.Product;
import ru.geekbrains.SpringwebApp.model.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("select distinct p.id, p.name, p.cost from Users_products up left join Product p on p.id = up.product_id where up.user_id = :id")
    @Query(value = "SELECT PRODUCT.id, PRODUCT.name, PRODUCT.cost from USERS_PRODUCT left join PRODUCT P on P.ID = USERS_PRODUCT.PRODUCT_ID where USERS_PRODUCT.user_id = :id", nativeQuery = true)
    List<Product> whatBought(long id);

}
