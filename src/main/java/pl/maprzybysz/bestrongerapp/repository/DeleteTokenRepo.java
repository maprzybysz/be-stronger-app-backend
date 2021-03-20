package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maprzybysz.bestrongerapp.Entity.DeleteToken;

import java.util.Optional;


@Repository
public interface DeleteTokenRepo extends JpaRepository<DeleteToken, Long> {
    Optional<DeleteToken> findByValue(String value);
    Optional<DeleteToken> findDeleteTokenByAppUserId(Long id);
    void deleteByValue(String value);
}
