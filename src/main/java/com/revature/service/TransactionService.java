package com.revature.service;

import java.util.Set;

import org.apache.log4j.Logger;
import com.revature.model.Transaction;
import com.revature.model.User;

/**
 * 
 * @author jingyu
 * This class has all the necessary methods which top controller will use.
 *
 */
public class TransactionService {

	private static Logger logger = Logger.getLogger(TransactionService.class);
	
	  public static enum TransactionAction {
		  VIEW,
		  INSERT
	  }
	  
	  public Set<Transaction> viewAllTransactionsForThisUser(TransactionAction action, User user){
		  
		     TransactionSearchService transactionSearch = new TransactionSearchService();
		     
		     logger.trace("We are in transactionservice now. A list of transactions will be returned back.");
		     
		     Set<Transaction> transactionSet = transactionSearch.transactionSearch(user);
		     
		     logger.trace("We are in transactionservice now, transaction data has been sent back sucessfully.");
		     
		     return transactionSet;
		     
	  }
	  
	  
	  public boolean insertTransaction(TransactionAction action, Transaction transaction){
		  
		  TransactionInsertService transactionInsert = new TransactionInsertService();
		  
		  boolean insertSuccess;
		  
		  insertSuccess = transactionInsert.transactionInsert(transaction);
		  
		  return insertSuccess;
	  }
}
