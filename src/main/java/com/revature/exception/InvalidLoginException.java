package com.revature.exception;

/**
 * 
 * @author jingyu
 * This excpetion is used when login is failed.
 *
 */
public class InvalidLoginException extends Exception {

	public InvalidLoginException(String message){
		  super(message);
	  }
	
}
