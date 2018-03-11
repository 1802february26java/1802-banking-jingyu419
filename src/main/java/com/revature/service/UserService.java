package com.revature.service;

import org.apache.log4j.Logger;

import com.revature.exception.InvalidLoginException;
import com.revature.model.User;

public class UserService {
 
	private static Logger logger = Logger.getLogger(UserService.class);
	
	  public static enum UserAction {
		  LOGIN,
		  REGISTER,
		  SEARCH
	  }
	  
	  public boolean userLogin(UserAction action, User user) throws InvalidLoginException{
		    
		  UserLoginService userLoginService = new UserLoginService();
		  
		  User resultUser = userLoginService.userLogin(user);
		  
		     if(resultUser ==null){
		    	 
		    	 throw new InvalidLoginException("Wrong username and password combination.");
		     }
		  
			  if(user.getPassword().equals(resultUser.getPassword())){
				  
				  logger.trace("password is matched.");
				  
				  return true;
				  
			  }
			  
			  return false; 
	  }
	  
	  public boolean userRegister(UserAction action, User user){
		  
		   UserInsertService userInsert = new UserInsertService();
		   
		   return userInsert.userInsert(user);
		   
	  }
	  
	  public User userSearch(UserAction action, User user){
		  
		   UserSearchService userSearch = new UserSearchService();
		   
		   User resultUser = userSearch.userSearch(user.getUserName());
		   
		   return resultUser;
	  }
	  
}
