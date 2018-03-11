package com.revature.exception;

/**
 * 
 * @author jingyu
 * This exception is used when the user enters an incorrect menu choice.
 */
public class InvalidMenuChoiceException extends Exception{

	  public InvalidMenuChoiceException(String message){
		  super(message);
	  }
}
