package com.tmobile.domain.modification;

public interface UserService {

	
	User findUserByName(String name);
	User findById(Long id);
}
