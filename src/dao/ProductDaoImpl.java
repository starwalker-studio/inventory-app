package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dbmain.EmbedDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static environment.Constants.*;

import model.Product;

public class ProductDaoImpl implements ProductDao {
	
	private Connection connect;

	private PreparedStatement preparedStatement;

	private ResultSet rs;
	
	private SimpleDateFormat simpleDateFormat;
	
	private String date;

	@Override
	public ObservableList<Product> findAll() throws SQLException {
		embedDBConection();
		preparedStatement = connect.prepareStatement(SELECT_DATA);
		rs = preparedStatement.executeQuery();
		Product product;
		ObservableList<Product> listData = FXCollections.observableArrayList();
		while (rs.next()) {
			product = new Product(rs.getInt(INVENTORY_COLUMNS_TABLE[0]), rs.getString(INVENTORY_COLUMNS_TABLE[1]), rs.getString(INVENTORY_COLUMNS_TABLE[2]),
					rs.getString(INVENTORY_COLUMNS_TABLE[3]), rs.getString(INVENTORY_COLUMNS_TABLE[4]), rs.getString(INVENTORY_COLUMNS_TABLE[5]),
					rs.getString(INVENTORY_COLUMNS_TABLE[6]), rs.getString(INVENTORY_COLUMNS_TABLE[7]));
			listData.add(product);
		}
		return listData;
	}

	@Override
	public void insertProduct(Product product) throws SQLException {
		embedDBConection();
		preparedStatement = connect.prepareStatement(INSERT_DATA);
		preparedStatement.setString(1, product.getProductCode());
		preparedStatement.setString(2, product.getProductName());
		preparedStatement.setString(3, product.getBrand());
		preparedStatement.setString(4, product.getStatus());		
		preparedStatement.setString(5, getSystemDate());
		preparedStatement.setString(6, product.getQuantify());
		preparedStatement.setString(7, product.getPrice());
		preparedStatement.executeUpdate();
	}

	@Override
	public void updateProduct(Product product) throws SQLException {
		embedDBConection();
		preparedStatement = connect.prepareStatement(UPDATE_PRODUCT);
		preparedStatement.setString(1, product.getProductName());
		preparedStatement.setString(2, product.getBrand());
		preparedStatement.setString(3, product.getStatus());		
		preparedStatement.setString(4, getSystemDate());
		preparedStatement.setString(5, product.getQuantify());
		preparedStatement.setString(6, product.getPrice());
		preparedStatement.setString(7, product.getProductCode());
		preparedStatement.executeUpdate();
	}

	@Override
	public void deleteProduct(Product product) throws SQLException {
		embedDBConection();
		preparedStatement = connect.prepareStatement(DELETE_PRODUCT);
		preparedStatement.setString(1, product.getProductCode());
		preparedStatement.executeUpdate();
	}
	
	private String getSystemDate() {
		simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
		date = simpleDateFormat.format(new Date());
		return date;
	}
	
	@Override
	public boolean findByProductCode(Product product) throws SQLException {
		embedDBConection();
		preparedStatement = connect.prepareStatement(SEARCH_BY_PRODUCT_CODE);
		preparedStatement.setString(1, product.getProductCode());
		rs = preparedStatement.executeQuery();		
		return rs.next();		
	}
	
	private void embedDBConection() {
		connect = EmbedDB.connect();
	}

}
