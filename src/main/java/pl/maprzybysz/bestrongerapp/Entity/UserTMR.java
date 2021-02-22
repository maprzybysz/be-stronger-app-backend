package pl.maprzybysz.bestrongerapp.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UserTMR { //TMR-Total metabolic Rate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int tmr;
    private int protein;
    private int carbohydrates;
    private int fat;
    private LocalDate dateAdded;
    @ManyToOne
    @JoinColumn(name="app_user_details_id")
    private AppUserDetails appUserDetails;
}
