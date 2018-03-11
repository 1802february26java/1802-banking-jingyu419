package com.revature.service;

import org.apache.log4j.Logger;
import com.revature.model.User;
import com.revature.repository.UserRepositoryJdbc;

/**
 * 
 * @author jingyu
 * This class is used to search user.
 *
 */
public class UserSearchService {

	 private static Logger logger = Logger.getLogger(UserSearchService.class);
	    
	    public User userSearch(String userName){
	    	
	    	UserRepositoryJdbc repository = new UserRepositoryJdbc();
	    	
	    	logger.trace("We are in UserSearchService, return user information.");
	    	
	    	User user = repository.findByUserName(userName);
	    	
	    	logger.trace("UserSearchService: "+user);
	    	
			return user; 
	    }
	    
}
