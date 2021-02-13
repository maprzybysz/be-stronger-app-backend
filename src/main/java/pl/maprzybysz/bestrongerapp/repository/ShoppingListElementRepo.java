package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maprzybysz.bestrongerapp.model.ShoppingListElement;

public interface ShoppingListElementRepo extends JpaRepository<ShoppingListElement, Long > {
}
