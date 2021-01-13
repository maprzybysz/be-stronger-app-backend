package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maprzybysz.bestrongerapp.model.VerificationToken;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByValue(String value);
    void deleteByValue(String value);
}
