package pl.maprzybysz.bestrongerapp.service;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.maprzybysz.bestrongerapp.Entity.AppUser;
import pl.maprzybysz.bestrongerapp.repository.AppUserRepo;

import java.util.Optional;


@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private AppUserRepo appUserRepo;

	@Autowired
	public UserDetailsServiceImpl(AppUserRepo appUserRepo) {
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