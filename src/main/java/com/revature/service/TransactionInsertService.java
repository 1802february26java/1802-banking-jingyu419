package com.revature.service;

import org.apache.log4j.Logger;

import com.revature.model.Transaction;
import com.revature.repository.TransactionRepositoryJdbc;

/**
 * 
 * @author jingyu
 * This class insert a new transaction
 *
 */
public class TransactionInsertService {

	private static Logger logger = Logger.getLogger(TransactionInsertService.class);
    
    public boolean transactionInsert(Transaction transaction){
    	
    	TransactionRepositoryJdbc repository = new TransactionRepositoryJdbc();
    	
    	logger.trace("We are in TransactionInsertService, return true when insert is sucessfully.");
    	
    	return repository.insert(transaction);
    }
}
