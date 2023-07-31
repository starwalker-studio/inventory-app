package dao;

import java.sql.SQLException;

import model.LoginUser;

public interface LoginUserDao {
	
	public boolean isUser(LoginUser user) throws SQLException;
	
	public boolean userExist(LoginUser user) throws SQLException;
	
	public void createUser(LoginUser user) throws SQLException;

}
