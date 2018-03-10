package com.revature.repository;

import java.util.Set;

import com.revature.model.Account;

public interface AccountRepository {

	  public boolean insert(Account account);
	  
	  public boolean update(Account account);
	  
	  public Set<Account> findAccountByUserId(Long userId);
	  
}
