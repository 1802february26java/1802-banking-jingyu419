package com.revature.service;

import org.apache.log4j.Logger;

import com.revature.model.User;
import com.revature.repository.UserRepositoryJdbc;

/**
 * 
 * @author jingyu
 * This class is used to check if the user is existed in database or not.
 *
 */
public class UserLoginService {

	    private static Logger logger = Logger.getLogger(UserLoginService.class);
	    
	    public User userLogin(User user){
	    	
	    	UserRepositoryJdbc repository = new UserRepositoryJdbc();
	    	
	    	logger.trace("We are in UserLoginService, return User object back to UserController class now");
	    	
	    	User result = repository.findByUserName(user.getUserName());
	    	
	    	logger.trace("UserLoginService: "+result);
	    	
			return result;
	    }
	    
}
