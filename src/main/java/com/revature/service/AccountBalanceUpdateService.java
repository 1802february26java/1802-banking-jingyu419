package com.revature.service;

import java.util.Set;
import org.apache.log4j.Logger;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.repository.AccountRepositoryJdbc;

/**
 * 
 * @author jingyu
 * This class update account balance. 
 *
 */
public class AccountBalanceUpdateService {

	 private static Logger logger = Logger.getLogger(AccountBalanceUpdateService.class);
	    
	    public boolean accountBalanceUpate(Account account){
	    	
	    	AccountRepositoryJdbc repository = new AccountRepositoryJdbc();
	    	
	    	logger.trace("We are in AccountBalanceUpdateService, return true when update is sucessfully.");
	    	
	    	return repository.update(account);
	    }
	    
}
