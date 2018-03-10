package com.revature.service;

import org.apache.log4j.Logger;
import com.revature.model.User;
import com.revature.repository.UserRepositoryJdbc;

public class UserInsertService {

	 private static Logger logger = Logger.getLogger(UserLoginService.class);
	    
	    public boolean userInsert(User user){
	    	
	    	UserRepositoryJdbc repository = new UserRepositoryJdbc();
	    	
	    	logger.trace("We are in UserInsertService, return true when insert is sucessfully.");
	    	
			return repository.insert(user);
	    }
	    
}
