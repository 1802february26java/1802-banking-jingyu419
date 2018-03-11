package com.revature.test;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.service.AccountService;


public class AccountServiceTest {

	private static Logger logger = Logger.getLogger(AccountServiceTest.class);
	
	private AccountService accountServiceTest;

	private User userTestSuccess;
	
	
	//Will execute before every @Test
	@Before
	public void setUp() {
		
		accountServiceTest = new AccountService();
		
		userTestSuccess = new User(4L,"test2","user","testuser2","password");
		
	}
	
	@Test
	public void accountSearchSuccessTest() {
		
		logger.trace("Testing accountSearchSuccess.");
	
		Set<Account> accountSet = accountServiceTest.viewAccountBalance(AccountService.AccountAction.VIEW, userTestSuccess);
		
		Account resultAccount = new Account();
		
		for(Account a : accountSet){
			
			resultAccount = a;
		    	
		}
		
		assertTrue(resultAccount.getBalance() == 500);
		
	}
}
