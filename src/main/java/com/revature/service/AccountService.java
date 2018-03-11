package com.revature.service;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Account;
import com.revature.model.User;

/**
 * 
 * @author jingyu
 * This class has necessary methods which controller will use.
 *
 */
public class AccountService {
	
	private static Logger logger = Logger.getLogger(AccountService.class);
	
	  public static enum AccountAction {
		  VIEW,
		  DEPOSIT,
		  WITHDRAW,
		  INSERT
	  }
	  
	  public Set<Account> viewAccountBalance(AccountAction action, User user){
		  
		     AccountSearchService accountSearch = new AccountSearchService();
		     
		     logger.trace("We are in accountservice now. A list of accounts will be returned back.");
		     
		     Set<Account> accountSet = accountSearch.accountSearch(user);;
		     
		     logger.trace("We are in Accountservice now, account data has been sent back sucessfully.");
		     
		     return accountSet;
		     
	  }
	  
	  public boolean depositAccount(AccountAction action, Account account){
		  
		     AccountBalanceUpdateService accountBalanceUpdate = new AccountBalanceUpdateService();
		     
		     boolean updateSuccess = accountBalanceUpdate.accountBalanceUpate(account);
		     
		     if(updateSuccess){
		    	 logger.trace("We are in accountservice. Deposit has been completed successully.");   	 
		     }
		     return updateSuccess;
		     
	  }
	  
	  public boolean withdrawAccount(AccountAction action, Account account){
		  
		  AccountBalanceUpdateService accountBalanceUpdate = new AccountBalanceUpdateService();
		     
		     boolean updateSuccess = accountBalanceUpdate.accountBalanceUpate(account);
		     
		     if(updateSuccess){
		    	 logger.trace("We are in accountservice. Withdraw has been completed successully.");       	 
		     }
		    
		     return updateSuccess;
	  }
	  
	  public boolean insertAccount(AccountAction action, Account account){
		  
		  AccountInsertService accountInsert = new AccountInsertService();
		  
		  boolean insertSuccess;
		  
		  insertSuccess = accountInsert.accountInsert(account);
		  
		  return insertSuccess;
	  }
	  
}
