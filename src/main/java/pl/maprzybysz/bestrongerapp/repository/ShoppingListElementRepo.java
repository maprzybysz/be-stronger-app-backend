package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maprzybysz.bestrongerapp.Entity.ShoppingListElement;


@Repository
public interface ShoppingListElementRepo extends JpaRepository<ShoppingListElement, Long > {
}
