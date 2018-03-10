package com.revature.service;

import java.util.Set;
import org.apache.log4j.Logger;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.repository.AccountRepositoryJdbc;

public class AccountSearchService {

	  private static Logger logger = Logger.getLogger(AccountSearchService.class);
	    
	    public Set<Account> accountSearch(User user){
	    	
	    	AccountRepositoryJdbc repository = new AccountRepositoryJdbc();
	    	
	    	logger.trace("We are in AccountSearchService, return a set of accounts.");
	    	
	    	return repository.findAccountByUserId(user.getUserId());
	    }
	   
}
