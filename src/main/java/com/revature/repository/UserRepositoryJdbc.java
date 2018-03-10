package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserRepositoryJdbc implements UserRepository {

	private static Logger logger = Logger.getLogger(UserRepositoryJdbc.class);
	
	@Override
	public boolean insert(User user) {
		
        logger.trace("Inserting new user.");
		
		try(Connection connection = ConnectionUtil.getConnection()) {
			
			int parameterIndex = 0;

			String sql = "INSERT INTO USERS(U_ID,U_FIRST_NAME,U_LAST_NAME,U_USER_NAME,U_PASS_WORD) VALUES(NULL,?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(++parameterIndex, user.getFirstName());
			statement.setString(++parameterIndex, user.getLastName());
			statement.setString(++parameterIndex, user.getUserName());
			statement.setString(++parameterIndex, user.getPassword());

			if(statement.executeUpdate() > 0) {
				logger.trace("Successfully insert new user.");
				return true;
			}
			
		} catch (SQLException e) {
			
			logger.error("Exception thrown while inserting new user", e);
		}
		
		return false;
		
	}

	@Override
	public User findByUserName(String userName) {
		
		logger.trace("Find username with username.");
		
		try(Connection connection = ConnectionUtil.getConnection()) {
			
			int parameterIndex = 0;
			
			String sql = "SELECT * FROM USERS WHERE U_USER_NAME = ?";	
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(++parameterIndex, userName);
			
			ResultSet result = statement.executeQuery();
		
			if(result.next()) {
				return new User(
						result.getLong("U_ID"),
						result.getString("U_FIRST_NAME"),
						result.getString("U_LAST_NAME"),
						result.getString("U_USER_NAME"),
						result.getString("U_PASS_WORD")
						);
			}
			
		} catch (SQLException e) {
			logger.error("Error while selecting user with this username.", e);
		}
		
		return null;
	}
	

}
