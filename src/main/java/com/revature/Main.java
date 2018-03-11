package com.revature;

import com.revature.controller.MainMenuController;


/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {
	
	public static void main (String[] args){
		
		MainMenuController controller = new MainMenuController();
		
		controller.startApp();
		
	}
}
