package pl.maprzybysz.bestrongerapp.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class AppUser implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false, unique = true)
	private String email;
	private boolean isEnabled;
	private boolean rulesAccepted;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Role> roles;
	@OneToMany(mappedBy = "appUser")
	private List<ShoppingListElement> shoppingList;
	@OneToMany(mappedBy = "appUser")
	private List<EatenMeal> eatenMeals;
	@OneToMany(mappedBy = "appUser")
	private List<Meal> createMeals;
	@OneToOne(mappedBy = "appUser")
	private AppUserDetails userDetails;
	@OneToMany(mappedBy = "appUser")
	private List<Article> createArticles;
	@OneToMany(mappedBy = "appUser")
	private List<Training> trainings;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAccountEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}

	public boolean isRulesAccepted() {
		return rulesAccepted;
	}

	public void setRulesAccepted(boolean rulesAccepted) {
		this.rulesAccepted = rulesAccepted;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role ur : roles) {
			authorities.add(new SimpleGrantedAuthority(ur.getRole()));
		}
		return authorities;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public List<ShoppingListElement> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(List<ShoppingListElement> shoppingList) {
		this.shoppingList = shoppingList;
	}

	public List<EatenMeal> getEatenMeals() {
		return eatenMeals;
	}

	public void setEatenMeals(List<EatenMeal> eatenMeals) {
		this.eatenMeals = eatenMeals;
	}

	public List<Meal> getCreateMeals() {
		return createMeals;
	}

	public void setCreateMeals(List<Meal> createMeals) {
		this.createMeals = createMeals;
	}

	public AppUserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(AppUserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public List<Article> getCreateArticles() {
		return createArticles;
	}

	public void setCreateArticles(List<Article> createArticles) {
		this.createArticles = createArticles;
	}

	public List<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<Training> trainings) {
		this.trainings = trainings;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public String toString() {
		return "AppUser{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", isEnabled=" + isEnabled +
				", rulesAccepted=" + rulesAccepted +
				", roles=" + roles +
				", shoppingList=" + shoppingList +
				", eatenMeals=" + eatenMeals +
				", createMeals=" + createMeals +
				", userDetails=" + userDetails +
				", createArticles=" + createArticles +
				'}';
	}
}