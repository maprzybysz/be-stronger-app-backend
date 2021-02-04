package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.maprzybysz.bestrongerapp.model.AppUser;
import pl.maprzybysz.bestrongerapp.repository.AppUserRepo;

import java.util.Optional;


@Primary
@Service
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
