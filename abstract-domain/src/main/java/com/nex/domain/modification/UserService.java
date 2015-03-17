package com.nex.domain.modification;

public interface UserService {

	
	User findUserByName(String name);
	User findById(Long id);
}
