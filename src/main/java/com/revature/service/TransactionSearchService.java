package com.revature.service;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.repository.TransactionRepositoryJdbc;

/**
 * 
 * @author jingyu
 * This class search all transactions which are related to this user.
 *
 */
public class TransactionSearchService {

	private static Logger logger = Logger.getLogger(TransactionSearchService.class);
    
    public Set<Transaction> transactionSearch(User user){
    	
    	TransactionRepositoryJdbc repository = new TransactionRepositoryJdbc();
    	
    	logger.trace("We are in TransactionSearchService, return a set of transactions.");
    	
    	Set<Transaction> transactionSet = repository.findAllTransaction(user.getUserId());
    	
    	logger.trace("We are in TransactionSearchService, transaction data has been sent back sucessfully.");
    	
    	return transactionSet; 
    }
    
}
