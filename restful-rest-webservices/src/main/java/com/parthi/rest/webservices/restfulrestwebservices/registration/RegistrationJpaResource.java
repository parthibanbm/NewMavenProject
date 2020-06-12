package com.parthi.rest.webservices.restfulrestwebservices.registration;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.parthi.rest.webservices.restfulrestwebservices.registration.LoginRequest;
import com.parthi.rest.webservices.restfulrestwebservices.registration.JwtResponse;
import com.parthi.rest.webservices.restfulrestwebservices.registration.UserDetailsImpl;


//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RegistrationJpaResource {
	
	@Autowired
	AuthenticationManager authenticationManager;


	@Autowired
	private RegistrationJpaRepository registrationJpaRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;

		
	/*
	 * @PostMapping("/jpa/users/registration") public ResponseEntity<Void>
	 * createR(@RequestBody Registration registration) {
	 * 
	 * registration.setUsername(registration.getUsername());
	 * registration.setPassword(registration.getPassword()); Registration
	 * createdTodo = registrationJpaRepository.save(registration); URI uri =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (createdTodo.getId()) .toUri(); return ResponseEntity.created(uri).build();
	 * 
	 * }
	 */
	
	@PostMapping("/jpa/users/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		/*
		 * List<String> roles = userDetails.getAuthorities().stream() .map(item ->
		 * item.getAuthority()) .collect(Collectors.toList());
		 */

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername() 
												 //,userDetails.getEmail(), 
												// roles
												 ));
	}
	
	
	
	@PostMapping("/jpa/users/registration")
	public ResponseEntity<Void> createUser(@RequestBody Registration registration) {
		
		registration.setUsername(registration.getUsername());
		registration.setPassword(registration.getPassword());
		
		@SuppressWarnings("unused")
		Registration user = new Registration(registration.getUsername(),				
				 encoder.encode(registration.getPassword()));
		
		
		Registration createdTodo = registrationJpaRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

	

}
