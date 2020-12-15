package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.maprzybysz.bestrongerapp.model.AppUser;
import pl.maprzybysz.bestrongerapp.repository.AppUserRepo;

import java.util.Optional;


public class AppUserDetailsServiceImpl implements UserDetailsService {
	
	private AppUserRepo appUserRepo;
	
	@Autowired
	public AppUserDetailsServiceImpl(AppUserRepo appUserRepo) {
		this.appUserRepo = appUserRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<AppUser> user = appUserRepo.findByUsername(username);
		if(user.isEmpty()){
			throw new UsernameNotFoundException(username);
		}else {
			return user.get();
		}
	}
}
