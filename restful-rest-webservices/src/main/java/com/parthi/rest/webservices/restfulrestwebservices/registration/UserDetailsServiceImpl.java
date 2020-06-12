package com.parthi.rest.webservices.restfulrestwebservices.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parthi.rest.webservices.restfulrestwebservices.registration.Registration;
import com.parthi.rest.webservices.restfulrestwebservices.registration.RegistrationJpaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	RegistrationJpaRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Registration user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

}
