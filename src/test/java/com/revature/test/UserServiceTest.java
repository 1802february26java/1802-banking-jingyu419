package com.revature.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.revature.exception.InvalidLoginException;
import com.revature.model.User;
import com.revature.service.UserService;

public class UserServiceTest {
		
		private static Logger logger = Logger.getLogger(UserServiceTest.class);
		
		private UserService userServiceTest;

		private User userTestSuccess;
		
		private User userTestFail;
		
		
		//Will execute before every @Test
		@Before
		public void setUp() {
			
			userServiceTest = new UserService();
			
			userTestSuccess = new User(4L,"test2","user","testuser2","password");
			
			userTestFail = new User(4L,"test","test","test","test");
		}
		
		
		@Test
		public void userLoginSuccessTest() {
			
			logger.trace("Testing userLoginSuccess.");
		    
			 boolean loginSuccess = false;
		        
		        try{
		        	
		        	loginSuccess = userServiceTest.userLogin(UserService.UserAction.LOGIN,userTestSuccess);
		        	
		        }catch(InvalidLoginException e){
		        	logger.info(e);
		        }
		        
			assertTrue(loginSuccess);
			
		}
		
		
		@Test
		public void userLoginFailTest() {
			
			logger.trace("Testing userLoginFail.");
			
			 boolean loginFail = false;
		        
		        try{
		        	
		        	loginFail = userServiceTest.userLogin(UserService.UserAction.LOGIN,userTestFail);
		        	
		        }catch(InvalidLoginException e){
		        	logger.info(e);
		        }
		        
			assertFalse(loginFail);

			
		}
		
		
		@Test
		public void userSearchTest(){
			 
			 logger.trace("Testing user Search.");
			 
			 boolean searchTest = false;
		        
		        try{
		        	
		        	searchTest = userServiceTest.userLogin(UserService.UserAction.LOGIN,
		        			userServiceTest.userSearch(UserService.UserAction.SEARCH, userTestSuccess));
	        	
		        }catch(InvalidLoginException e){
		        	logger.info(e);
		        }
		        
			 assertTrue(searchTest);
		}
		
}
