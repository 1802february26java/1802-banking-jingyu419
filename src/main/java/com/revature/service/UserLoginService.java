package com.revature.service;

import org.apache.log4j.Logger;
import com.revature.model.User;
import com.revature.repository.UserRepositoryJdbc;

public class UserLoginService {

	    private static Logger logger = Logger.getLogger(UserLoginService.class);
	    
	    public User userLogin(User user){
	    	
	    	UserRepositoryJdbc repository = new UserRepositoryJdbc();
	    	logger.trace("We are in UserLoginService, return User object back to UserController class now");
			return repository.findByUserName(user.getUserName());
	    }
}
