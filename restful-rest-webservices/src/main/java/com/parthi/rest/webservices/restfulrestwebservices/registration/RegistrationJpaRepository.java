package com.parthi.rest.webservices.restfulrestwebservices.registration;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationJpaRepository extends CrudRepository<Registration, Long>{
	
	Optional<Registration> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
}
