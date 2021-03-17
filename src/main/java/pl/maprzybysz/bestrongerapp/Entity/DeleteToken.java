package pl.maprzybysz.bestrongerapp.Entity;

import javax.persistence.*;

@Entity
public class DeleteToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    @OneToOne(cascade = CascadeType.REMOVE)
    private AppUser appUser;

    public DeleteToken(String value, AppUser appUser) {
        this.value = value;
        this.appUser = appUser;
    }

    public DeleteToken() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public AppUser getAppUser() {
        return appUser;
    }
    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "DeleteToken{" +
                "id=" + id +
                ", value='" + value + '}';
    }
}


