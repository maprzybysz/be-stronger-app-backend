package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maprzybysz.bestrongerapp.model.AppUser;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
	Optional<AppUser> findByUsername(String username);
	
	Optional<AppUser> findByEmail(String email);
	
	Optional<AppUser> findById(Long id);
}
