package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maprzybysz.bestrongerapp.Entity.RecoveryToken;
import pl.maprzybysz.bestrongerapp.Entity.VerificationToken;

import java.util.Optional;

@Repository
public interface RecoveryTokenRepo extends JpaRepository<RecoveryToken, Long> {
    Optional<RecoveryToken> findByValue(String value);
    void deleteByValue(String value);
}
