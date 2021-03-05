package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maprzybysz.bestrongerapp.Entity.UserTMR;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserTMRRepo extends JpaRepository<UserTMR, Long> {
    public List<UserTMR> findUserTMRByDateAddedIsLessThanEqualAndAppUserDetailsId(LocalDate date, Long id);
    public List<UserTMR> findUserTMRByDateAddedIsGreaterThanAndAppUserDetailsId(LocalDate date, Long id);
}
