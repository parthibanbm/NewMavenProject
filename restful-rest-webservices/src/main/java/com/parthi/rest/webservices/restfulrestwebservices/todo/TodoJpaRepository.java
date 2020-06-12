package com.parthi.rest.webservices.restfulrestwebservices.todo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoJpaRepository extends CrudRepository<Todo, Long>{
	
	List<Todo> findByUsername(String username);

}
