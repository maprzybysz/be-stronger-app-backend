package pl.maprzybysz.bestrongerapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ShoppingListElement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String listElement;

    @ManyToOne
    @JoinColumn(name="app_user_id")
    @JsonIgnore
    private AppUser appUser;

    public ShoppingListElement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListElement() {
        return listElement;
    }

    public void setListElement(String listElement) {
        this.listElement = listElement;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
