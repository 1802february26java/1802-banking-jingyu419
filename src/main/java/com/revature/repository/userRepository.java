package com.revature.repository;

import java.util.Set;

import com.revature.model.User;

public interface UserRepository {

	  public boolean insert(User user);
	  
	  public User findByUserName(String userName);
	  
}
