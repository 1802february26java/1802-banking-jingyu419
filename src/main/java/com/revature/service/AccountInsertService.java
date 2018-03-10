package com.revature.service;

import org.apache.log4j.Logger;
import com.revature.model.Account;
import com.revature.repository.AccountRepositoryJdbc;

public class AccountInsertService {

	 private static Logger logger = Logger.getLogger(AccountInsertService.class);
	    
	    public boolean accountInsert(Account account){
	    	
	    	AccountRepositoryJdbc repository = new AccountRepositoryJdbc();
	    	
	    	logger.trace("We are in AccountInsertService, return true when insert is sucessfully.");
	    	
	    	return repository.insert(account);
	    }
	    
}
