package com.revature;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.controller.UserController;
import com.revature.controller.UserController.UserAction;
import com.revature.model.User;

/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {

	private static Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		logger.info("Thank you to use the Bank App. What can I do for you today?");
		//while(true){
		Scanner scanner = new Scanner(System.in);
		logger.info("Please enter '1' if you want to login.");
		logger.info("Please enter '2' if you want to register.");
		String input = scanner.nextLine();

		
		if(Integer.parseInt(input)==1){
			
        		boolean loginSuccess = loginService();
        		
        		    while(loginSuccess == false){
        		    	logger.info("Wrong username and password combination. Please try again.");
        		    	loginService();
        		    }   
        		    
		}
		scanner.close();
		//}
	}
	
	private static boolean loginService(){
		Scanner scanner = new Scanner(System.in);
		logger.info("Please enter your username: ");
		String username = scanner.nextLine();
		logger.info("Please enter your password: ");
		String password = scanner.nextLine();
		scanner.close();
		User user = new User(100L,"","",username,password,100L);
		UserController userController = new UserController();
		return userController.getAction(UserAction.LOGIN,user);
	}
}
