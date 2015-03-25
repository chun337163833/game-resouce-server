package org.shovelgame.domain.service;

import org.shovelgame.domain.TxUser;
import org.shovelgame.domain.modification.User;
import org.shovelgame.domain.modification.UserService;

public class TaxiUserService implements UserService {

	@Override
	public User findUserByName(String name) {
		return (User) TxUser.findTxUsersByUserName(name);
	}

	@Override
	public User findById(Long id) {
		return (User) TxUser.findTxUser(id);
	}
	
}
