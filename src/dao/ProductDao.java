package dao;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import model.Product;

public interface ProductDao {

	public ObservableList<Product> findAll() throws SQLException;

	public boolean findByProductCode(Product product) throws SQLException;

	public void insertProduct(Product product) throws SQLException;

	public void updateProduct(Product product) throws SQLException;

	public void deleteProduct(Product product) throws SQLException;

}
