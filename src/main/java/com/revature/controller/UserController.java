package com.revature.controller;

import org.apache.log4j.Logger;

import com.revature.model.User;
import com.revature.service.UserLoginService;

public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);
	
	  public static enum UserAction {
		  LOGIN,
		  REGISTER
	  }
	  
	  public boolean getAction(UserAction action, User inputUser){
		  switch(action){
		  
		  case LOGIN :
			  logger.trace("UserController class: We are in login action now.");
			  UserLoginService userLoginService = new UserLoginService();
			  User resultUser = userLoginService.userLogin(inputUser);
			  if(inputUser.getPassword().equals(resultUser.getPassword())){
				  logger.trace("password is matched.");
				  return true;
			  }
			  else{
				  return false;
			  }
			  
		  case REGISTER :
			  return false;
			  
		   default:
			   // either handle it here or in main
			  return false;
		  }
	  }
}
