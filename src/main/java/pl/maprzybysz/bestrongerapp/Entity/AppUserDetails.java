package pl.maprzybysz.bestrongerapp.Entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class AppUserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sex;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private UserActivity userActivity;
    @Enumerated(EnumType.STRING)
    private UserGoal userGoal;
    private double height;
    @OneToMany(mappedBy = "appUserDetails", cascade = CascadeType.ALL)
    private List<UserWeight> weights = new ArrayList<>();
    @OneToMany(mappedBy = "appUserDetails")
    private List<UserTMR> tmrs;
    @OneToOne
    @JoinColumn(name="app_user_id")
    private AppUser appUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public UserActivity getUserActivity() {
        return userActivity;
    }

    public void setUserActivity(UserActivity userActivity) {
        this.userActivity = userActivity;
    }

    public UserGoal getUserGoal() {
        return userGoal;
    }

    public void setUserGoal(UserGoal userGoal) {
        this.userGoal = userGoal;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public List<UserWeight> getWeights() {
        return weights;
    }

    public void setWeights(List<UserWeight> weights) {
        this.weights = weights;
    }

    public double getLastWeight(){
        Collections.sort(weights, new Comparator<UserWeight>(){
            @Override
            public int compare(UserWeight o1, UserWeight o2){
                return o1.getDateAdded().compareTo(o2.getDateAdded());
            }
        });
        return weights.get(weights.size()-1).getWeight();

    }

    public List<UserTMR> getTmrs() {
        return tmrs;
    }
    public void setTmrs(List<UserTMR> tmrs) {
        this.tmrs = tmrs;
    }

    public UserTMR getLastTMR(){
        Collections.sort(tmrs, new Comparator<UserTMR>(){
            @Override
            public int compare(UserTMR o1, UserTMR o2){
                return o1.getDateAdded().compareTo(o2.getDateAdded());
            }
        });
        return tmrs.get(tmrs.size()-1);

    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

}
