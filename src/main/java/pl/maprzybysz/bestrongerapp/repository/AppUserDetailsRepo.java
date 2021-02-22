package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maprzybysz.bestrongerapp.Entity.AppUserDetails;

@Repository
public interface AppUserDetailsRepo extends JpaRepository<AppUserDetails, Long> {
}
