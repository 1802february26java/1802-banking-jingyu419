package com.revature.repository;

import java.util.Set;

import com.revature.model.Transaction;

public interface TransactionRepository {

	 public boolean insert(Transaction transaction);
	   
	 public Set<Transaction> findAllTransaction(Long userId);
	 
}
