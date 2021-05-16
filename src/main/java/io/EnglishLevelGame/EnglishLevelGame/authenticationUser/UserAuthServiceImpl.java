package io.EnglishLevelGame.EnglishLevelGame.authenticationUser;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class UserAuthServiceImpl implements UserDetailsService {
	
	@Autowired
	public UserRepo userAuthRepo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAuth user = userAuthRepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new User(user.getUsername(), user.getPassword(), user.getRole().getGrantedAuthorities());
	}

}
