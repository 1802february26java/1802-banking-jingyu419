package com.revature;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.controller.UserController;
import com.revature.controller.UserController.UserAction;
import com.revature.exception.InvalidInputException;
import com.revature.model.User;

/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {

	private static Logger logger = Logger.getLogger(Main.class);
	
	public static void main (String[] args) throws InvalidInputException{
		logger.info("Thank you to use the Bank App. What can I do for you today?");
		Scanner scanner = new Scanner(System.in);
		while(true){
		logger.info("Please enter '1' if you want to login.");
		logger.info("Please enter '2' if you want to register.");
		String input = scanner.nextLine();
		
		if(input.equals("1")){
			
        		boolean loginSuccess = loginService();
        		
        		    while(loginSuccess == false){
        		    	logger.info("Wrong username and password combination. Please try again.");
        		    	loginService();
        		    }   
        		    
		}
		else if(input.equals("2")){
			
		}
		else{
			logger.info("Please enter either 1 or 2.");
		}
			
		}
		//scanner.close();
	}
	
	private static boolean loginService(){
		
		Scanner scanner = new Scanner(System.in);
		
		logger.info("Please enter your username: ");
		String username = scanner.nextLine();
		logger.info("Please enter your password: ");
		String password = scanner.nextLine();
		
		scanner.close();
		
		User user = new User(3L,"","",username,password);
		
		UserController userController = new UserController();
		
		return userController.getAction(UserAction.LOGIN,user);
	}
}
