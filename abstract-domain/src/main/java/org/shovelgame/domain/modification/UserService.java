package org.shovelgame.domain.modification;

public interface UserService {

	
	User findUserByName(String name);
	User findById(Long id);
}
