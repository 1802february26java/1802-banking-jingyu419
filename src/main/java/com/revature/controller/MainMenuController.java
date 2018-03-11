package com.revature.controller;

import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.exception.InvalidLoginException;
import com.revature.exception.InvalidMenuChoiceException;
import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.service.AccountService;
import com.revature.service.TransactionService;
import com.revature.service.UserService;

/**
 * 
 * @author jingyu
 * This is the main controller class which receives inputs from console and calls necessary service class.
 *
 */
public class MainMenuController{

	private static Logger logger = Logger.getLogger(MainMenuController.class);
	private static Scanner scanner = new Scanner(System.in);
	
	private static UserService userService = new UserService();
	private static AccountService accountService = new AccountService();
	private static TransactionService transactionService = new TransactionService();
	
	private static User loggedInUser;
	private static Set<Account> loggedInUserAccount;
	private static Set<Transaction> loggedInUserTransaction;
	
	public void startApp(){
		
		logger.info("Thank you to use the Bank App. What can I do for you today?");
		
		initMainMenu();
	}
	
	private void initMainMenu(){
		
		logger.info("Please enter '1' if you want to login.");
		logger.info("Please enter '2' if you want to register.");
		
		String input = scanner.nextLine();
		
		try{
		loginOrRegister(input);}
		catch(Exception e){
			logger.info(e.getMessage());
		}
		finally{
			initMainMenu();
		}

	}
	
	private void loginOrRegister(String input) throws InvalidMenuChoiceException{
		
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
			
			      if(registerHelper()){
			    	  logger.info("Your account has been created.");
			    	  userMenu();
			      }
			       
		}
		  
		else{
			 throw new InvalidMenuChoiceException("Wrong menu choice. Please enter either 1 or 2.");
			
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
	     logger.info("Please enter '4' if you want to view your transaction.");
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
		
	       case "4":
	       logger.info(viewAllTransactions());
	       userMenu();
	       break;
	
	       default:
		   logger.info("Thank you to use the Bank App. Please come back next time.\n\n\n");
		
		   startApp();
		
	}

}

     
	private boolean login(User user){
		
        loggedInUser = userService.userSearch(UserService.UserAction.SEARCH, user);
        
        boolean loginSuccess = false;
        
        try{
        	
        	loginSuccess = userService.userLogin(UserService.UserAction.LOGIN,user);
        	
        }catch(InvalidLoginException e){
        	logger.info(e.getMessage());
        }
        
             logger.trace("login method: loginSuccess= "+loginSuccess);
             
		return loginSuccess; 
		
	}
	
	private boolean register(User user){
		
		boolean registerSuccess = false;
		
		registerSuccess = userService.userRegister(UserService.UserAction.REGISTER,user);
		
		loggedInUser = userService.userSearch(UserService.UserAction.SEARCH, user);
		
		Account account = new Account(null, loggedInUser.getUserId(), "Checking", 0D); 
				
		accountService.insertAccount(AccountService.AccountAction.INSERT, account);
		
		loggedInUserAccount = accountService.viewAccountBalance(AccountService.AccountAction.VIEW, loggedInUser);
		
		loggedInUserTransaction = transactionService.viewAllTransactionsForThisUser(TransactionService.TransactionAction.VIEW, loggedInUser);
		
		return registerSuccess;
		
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
			
			Transaction depositTransaction = new Transaction(null,loggedInUser.getUserId(),null,account.getAccountName(),"DEPOSIT",Double.parseDouble(moneyToDeposit));
			
			insertTransaction(depositTransaction);
			
			logger.trace("New deposit transaction has been created.");
						
			logger.info("Balance has been update. Below is your new acccount information.");					
			
			logger.info(viewBalance());
			
		}
		
		else{
		logger.info("You have multiple accounts. Which account you want to deposit?");
		
		Account account = multipleAccountUpdateHelper();
		
		logger.trace("We come back our multi account update helper class "+ account);
		
		account.setBalance(account.getBalance()+ Double.parseDouble(moneyToDeposit));
		
		logger.trace("We come back our multi account update helper class and update the balance"+ account);
		  
		deposit(account);
		
		Transaction depositTransaction = new Transaction(null,loggedInUser.getUserId(),null,account.getAccountName(),"DEPOSIT",Double.parseDouble(moneyToDeposit));
		
		insertTransaction(depositTransaction);
		
		logger.trace("New deposit transaction has been created.");
		
		logger.info("Balance has been update. Below is your new acccount information.");
		
		logger.info(viewBalance());
		
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
			
			while((account.getBalance()- Double.parseDouble(moneyToDeposit))<0){
				
				logger.info("Sorry, you cannot withdraw more than you have. Please enter a smaller number.");
				
				logger.info("How much money do you withdraw?");		
				
				moneyToDeposit = scanner.nextLine();
				
			}
			
			account.setBalance(account.getBalance()- Double.parseDouble(moneyToDeposit));
			
			withdraw(account);
			
            Transaction withdrawTransaction = new Transaction(null,loggedInUser.getUserId(),null,account.getAccountName(),"WITHDRAW",Double.parseDouble(moneyToDeposit));
			
			insertTransaction(withdrawTransaction);
			
			logger.trace("New withdraw transaction has been created.");
			
			logger.info("Balance has been update. Below is your new acccount information.");
			
			logger.info(viewBalance());
			
		}
		
		else{
			
			logger.info("You have multiple accounts. Which account you want to withdraw?");
			
			Account account = multipleAccountUpdateHelper();
			
			logger.trace("We come back our multi account update helper class "+ account);
			
			while((account.getBalance()- Double.parseDouble(moneyToDeposit))<0){
				
				logger.info("Sorry, you cannot withdraw more than you have. Please enter a smaller number.");
				
				logger.info("How much money do you withdraw?");			
				
				moneyToDeposit = scanner.nextLine();
				
			}
			
			account.setBalance(account.getBalance()- Double.parseDouble(moneyToDeposit));
			
			logger.trace("We come back our multi account update helper class and update the balance"+ account);
			
			deposit(account);
			
            Transaction withdrawTransaction = new Transaction(null,loggedInUser.getUserId(),null,account.getAccountName(),"WITHDRAW",Double.parseDouble(moneyToDeposit));
			
			insertTransaction(withdrawTransaction);
			
			logger.trace("New withdraw transaction has been created.");
			
			logger.info("Balance has been update. Below is your new acccount information.");
			
			logger.info(viewBalance());
		}
		
	}
	
	private boolean registerHelper(){
		
		boolean registerSuccess = false;
		
		logger.info("Please enter a username: ");
		
		String username = scanner.nextLine();
		
		while(isDuplicateUserName(username)){
			
			logger.info("This username has been taken.");
			
			logger.info("Please enter a username: ");
			
			username = scanner.nextLine();
		}
		
		logger.info("Please enter a password: ");
		String password = scanner.nextLine();
		
		logger.info("Please enter your first name: ");
		String firstname = scanner.nextLine();
		
		logger.info("Please enter your last name: ");
		String lastname = scanner.nextLine();
		
		
		User user = new User(null,firstname,lastname,username,password);
		
		registerSuccess = register(user);
		
		return registerSuccess;
	}
	
	private boolean isDuplicateUserName(String username){
		
		User user = new User(null,"","",username,"");
		
		if(userService.userSearch(UserService.UserAction.SEARCH, user) != null){
			return true;
		}
		return false;
		
	}
	
	private Account multipleAccountUpdateHelper(){

		boolean isThisAccountNameExisted = false;
		
		Account account = new Account();
		
		while(isThisAccountNameExisted == false){
			
		logger.info("Please type the account name: ");
		
		String accountName = scanner.nextLine();
		
		for(Account a : loggedInUserAccount){
			
		       isThisAccountNameExisted = a.getAccountName().equals(accountName);
		       
		       logger.trace(a.getAccountName()+ "input: "+accountName);
		       
		       if(isThisAccountNameExisted){
		    	   logger.trace(a+"  "+isThisAccountNameExisted);
		    	   account.setAccountName(a.getAccountName());
		    	   account.setAccountId(a.getAccountId());
		    	   account.setUserId(a.getUserId());
		    	   account.setBalance(a.getBalance());
		    	   break;
		       }
		     }
		if(isThisAccountNameExisted == false){
			logger.info("You did not enter account name correctly.Try again.");}
		}
		
			logger.trace("Before return.Find the account information..."+account);
		   return account;
		
	}
	
	   
	   private String viewAllTransactions(){
		   
		   String output = "";
			
		   loggedInUserTransaction = transactionService.viewAllTransactionsForThisUser(TransactionService.TransactionAction.VIEW, loggedInUser);		   
			
		   logger.trace("Transaction data has been sent back to MainMenu controller now");
			
		   for(Transaction t : loggedInUserTransaction){
				
				output += "Transaction time: "+t.getTransactionTime()+", account name: " +t.getAccountName()+ 
						", transaction action: "+t.getAction()+", amount:"+t.getAmount()+".\n";
				
			}
		    return output;
	   }
	   
	   private void insertTransaction(Transaction transaction){
		   
		   transactionService.insertTransaction(TransactionService.TransactionAction.INSERT, transaction);
		   
	   }
	   
	   
			
}
