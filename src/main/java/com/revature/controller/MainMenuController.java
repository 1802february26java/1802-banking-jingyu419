package com.revature.controller;

import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.service.AccountService;
import com.revature.service.UserService;
import com.revature.exception.InvalidLoginException;

public class MainMenuController{

	private static Logger logger = Logger.getLogger(MainMenuController.class);
	private static Scanner scanner = new Scanner(System.in);
	
	private static UserService userService = new UserService();
	private static AccountService accountService = new AccountService();
	
	private static User loggedInUser;
	private static Set<Account> loggedInUserAccount;
	
	public void startApp(){
		
		logger.info("Thank you to use the Bank App. What can I do for you today?");
		
		initMainMenu();
	}
	
	private void initMainMenu(){
		
		logger.info("Please enter '1' if you want to login.");
		logger.info("Please enter '2' if you want to register.");
		
		String input = scanner.nextLine();
		
		loginOrRegister(input);

	}
	
	private boolean login(User user){
		
        loggedInUser = userService.userSearch(UserService.UserAction.SEARCH, user);
        
        boolean loginSuccess = false;
        
        try{
        	
        	loginSuccess = userService.userLogin(UserService.UserAction.LOGIN,user);
        	
        }catch(InvalidLoginException e){
        	logger.info(e);
        }
        
             logger.trace("login method: loginSuccess= "+loginSuccess);
             
		return loginSuccess; 
		
	}
	
	private boolean register(User user){
		
		return false;
		
	}
	
	private String viewBalance(){
		
		String output = "";
		
		loggedInUserAccount = accountService.viewAccountBalance(AccountService.AccountAction.VIEW, loggedInUser);
		
		logger.trace("Account data has been sent back to MainMenu controller now");
		
		for(Account a : loggedInUserAccount){
			
			output += "Account name: "+a.getAccountName()+", balance: " +a.getBalance()+".\n";
			
		}
		 return output;
		
	}
	
	private void deposit(Account account){		
		
		accountService.depositAccount(AccountService.AccountAction.DEPOSIT, account);
		
	}
	
	private void withdraw(Account account){
		
		accountService.withdrawAccount(AccountService.AccountAction.WITHDRAW, account);
	}
	
	private void loginOrRegister(String input){
		
		  if(input.equals("1")){
				
	    		boolean loginSuccess = loginHelper();
	    		
	    		logger.trace("loginOrRegister: LoginSuccess= "+loginSuccess);
	    		
	    		    while(loginSuccess == false){
	    		    	
	    		    	logger.info("Wrong username and password combination. Please try again.");
	    		    	
	    		    	loginSuccess = loginHelper();
	    		    	
	    		    	
	    		    }

	                    userMenu();		    
	    		    
		}
		  else if(input.equals("2")){
			
		}
		else{
			logger.info("Please enter either 1 or 2.");
			initMainMenu();
		}
		  
	}
	
	private boolean loginHelper(){
						
			logger.info("Please enter your username: ");
			String username = scanner.nextLine();
			
			logger.info("Please enter your password: ");
			String password = scanner.nextLine();
			
			User user = new User(null,"","",username,password);
			
			boolean loginSuccess = login(user);
			
			logger.trace("loginHelper method: loginSuccess= "+loginSuccess);
			
			return loginSuccess;
		}
	
	private void userMenu(){
		
		logger.info("What do you want to do today?");
		logger.info("Please enter '1' if you want to view your balance.");
		logger.info("Please enter '2' if you want to deposit money.");
		logger.info("Please enter '3' if you want to withdraw money.");
		logger.info("Please enter any number if you want to exit.");
		String input = scanner.nextLine();
		
		userMenuHelper(input);
	}
	
	private void userMenuHelper(String input){
		
		switch(input){
		
		case "1":
			logger.info(viewBalance());
			userMenu();
			break;
			
		case "2":
			depositMenu();
			userMenu();
			break;
			
		case "3":
			withdrawMenu();
			userMenu();
			break;
		
		default:
			logger.info("Thank you to use the Bank App. Please come back next time.\n\n\n");
			
			startApp();
			
		}

	}
	
	private void depositMenu(){
		
		logger.info("Welcome to deposit money section.");
		logger.info("Below is your current account information.");
		
		logger.info(viewBalance());
		
		logger.info("How much money do you deposit?");
		
		String moneyToDeposit = scanner.nextLine();
		
		if(loggedInUserAccount.size() == 1){
			
			Account account = new Account();
			
			for(Account a: loggedInUserAccount){
			    account = a;
			}
			
			account.setBalance(account.getBalance()+ Double.parseDouble(moneyToDeposit));
			
			deposit(account);
			
			logger.info("Balance has been update. Below is your new acccount information.");
			
			logger.info(viewBalance());
			
		}
		
		else{
		logger.info("");
		}
		
	}
	
	private void withdrawMenu(){
		
		logger.info("Welcome to withdraw money section.");
		logger.info("Below is your current account information.");
		
		logger.info(viewBalance());
		
		logger.info("How much money do you withdraw?");
		
		String moneyToDeposit = scanner.nextLine();
		
		if(loggedInUserAccount.size() == 1){
			
			Account account = new Account();
			
			for(Account a: loggedInUserAccount){
			    account = a;
			}
			
			if((account.getBalance()- Double.parseDouble(moneyToDeposit))<0){
				logger.info("Sorry, you cannot withdraw more than you have.\n");
				withdrawMenu();
			}
			
			account.setBalance(account.getBalance()- Double.parseDouble(moneyToDeposit));
			
			withdraw(account);
			
			logger.info("Balance has been update. Below is your new acccount information.");
			
			logger.info(viewBalance());
			
		}
		
		else{
		logger.info("");
		}
		
	}
			
}
