package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maprzybysz.bestrongerapp.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRole(String role);
}
