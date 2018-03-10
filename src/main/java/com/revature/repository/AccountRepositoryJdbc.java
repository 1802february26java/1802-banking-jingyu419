package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class AccountRepositoryJdbc implements AccountRepository {

	private static Logger logger = Logger.getLogger(AccountRepositoryJdbc.class);
	
	@Override
	public boolean insert(Account account) {
		
		logger.trace("Inserting new account.");
		
		try(Connection connection = ConnectionUtil.getConnection()) {
			
			int parameterIndex = 0;
			
			String sql = "INSERT INTO ACCOUNTS(A_ID,U_ID,A_ACCOUNT_NAME,A_BALANCE) VALUES(NULL,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(++parameterIndex, account.getUserId());
			statement.setString(++parameterIndex,  account.getAccountName());
			statement.setDouble(++parameterIndex, account.getBalance());

			if(statement.executeUpdate() > 0) {
				logger.trace("Successfully insert new acccount.");
				return true;
			}
			
		} catch (SQLException e) {
			
			logger.error("Exception thrown while inserting new account", e);
		}
		
		return false;
		
	}

	@Override
	public boolean update(Account account) {
		
        logger.trace("Update account balance.");
		
		try(Connection connection = ConnectionUtil.getConnection()) {
			
			int parameterIndex = 0;
			
			String sql = "UPDATE ACCOUNTS SET A_BALANCE=? WHERE U_ID=? AND A_ACCOUNT_NAME=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setDouble(++parameterIndex, account.getBalance());
			statement.setLong(++parameterIndex,  account.getUserId());
			statement.setString(++parameterIndex, account.getAccountName());

			if(statement.executeUpdate() > 0) {
				logger.trace("Successfully update the balance.");
				return true;
			}
			
		} catch (SQLException e) {
			
			logger.error("Exception thrown while update account balance", e);
		}
		
		return false;
	
	}

	@Override
	public Set<Account> findAccountByUserId(Long userId) {

		logger.trace("Find all accounts related to this user Id.");
		
		try(Connection connection = ConnectionUtil.getConnection()) {
			
			int parameterIndex = 0;
			
			String sql = "SELECT * FROM ACCOUNTS WHERE U_ID = ?";	
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(++parameterIndex, userId);
			
			ResultSet result = statement.executeQuery();
		    
			Set<Account> set = new HashSet<>();
			
			while(result.next()) {
				set.add(new Account(
						result.getLong("A_ID"),
						result.getLong("U_ID"),
						result.getString("A_ACCOUNT_NAME"),
						result.getDouble("A_BALANCE")
						));
			}
			return set;
			
			
		} catch (SQLException e) {
			logger.error("Error while find user accounts with this user Id.", e);
		}
		
		return null;
	}
	
}
