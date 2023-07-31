package dao;

import model.LoginUser;

import static environment.Constants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbmain.EmbedDB;

public class LoginUserDaoImpl implements LoginUserDao {
	
	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet rs;

	@Override
	public boolean isUser(LoginUser user) throws SQLException {
		embedDBConnection();
		prepare = connect.prepareStatement(SELECT_WHERE_USER_AND_PASSWORD);
		prepare.setString(1, user.getUsername());
		prepare.setString(2, user.getPassword());
		rs = prepare.executeQuery();
		return rs.next();
	}

	@Override
	public boolean userExist(LoginUser user) throws SQLException  {
		embedDBConnection();
		prepare = connect.prepareStatement(SELECT_WHERE_USER);
		prepare.setString(1, user.getUsername());
		rs = prepare.executeQuery();
		return rs.next();
	}

	@Override
	public void createUser(LoginUser user) throws SQLException {
		embedDBConnection();
		prepare = connect.prepareStatement(INSERT_NEW_USER);
		prepare.setString(1, user.getUsername());
		prepare.setString(2, user.getPassword());
		prepare.executeUpdate();
	}

	public void embedDBConnection() {
		connect = EmbedDB.connect();
	}
	
}
