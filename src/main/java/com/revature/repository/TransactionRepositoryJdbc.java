package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Transaction;
import com.revature.util.ConnectionUtil;

public class TransactionRepositoryJdbc implements TransactionRepository {

	private static Logger logger = Logger.getLogger(TransactionRepositoryJdbc.class);
	
	@Override
	public boolean insert(Transaction transaction) {
		
		logger.trace("Inserting a new transaction.");
		
		try(Connection connection = ConnectionUtil.getConnection()) {
			
			int parameterIndex = 0;
			
     String sql = "INSERT INTO TRANSACTION(T_TRANSACTION_ID,T_USER_ID,T_DATE_TIME,T_ACCOUNT_NAME,T_AMOUNT,T_ACTION) VALUES (null,?,null,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(++parameterIndex, transaction.getUserId());
			statement.setString(++parameterIndex, transaction.getAccountName());
			statement.setDouble(++parameterIndex, transaction.getAmount());
			statement.setString(++parameterIndex, transaction.getAction());

			if(statement.executeUpdate() > 0) {
				
				return true;
				
			}
			
		} catch (SQLException e) {
			
			logger.error("Exception thrown while inserting a new transaction", e);
			
		}
		
		return false;
		
	}
	

	@Override
	public Set<Transaction> findAllTransaction(Long userId) {
			
		   logger.trace("Getting all transaction related to this user Id.");
			
		   try(Connection connection = ConnectionUtil.getConnection()) {
				
		   int parameterIndex = 0;
				
		   String sql = "SELECT * FROM TRANSACTION WHERE T_USER_ID = ?";
			
	        PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(++parameterIndex, userId);
			
			ResultSet result = statement.executeQuery();
		    
			Set<Transaction> set = new HashSet<>();
			
			while(result.next()) {
				set.add(new Transaction(
						result.getLong("T_TRANSACTION_ID"),						
						result.getLong("T_USER_ID"),
						result.getDate("T_DATE_TIME"),
						result.getString("T_ACCOUNT_NAME"),
						result.getString("T_ACTION"),
						result.getDouble("T_AMOUNT")
						));
			}
			logger.trace(set);
			return set;
			
			
		} catch (SQLException e) {
			logger.error("Error while find all transactions with this user Id.", e);
		}
		
		return null;
	}
	/*
	public static void main(String[] args){
		
		TransactionRepositoryJdbc repository = new TransactionRepositoryJdbc();
		logger.trace(repository.findAllTransaction(3L));
		logger.trace(repository.insert(new Transaction(null,3L,null,"checking 1","WITHDRAW",150D)));
	}
	*/

}
