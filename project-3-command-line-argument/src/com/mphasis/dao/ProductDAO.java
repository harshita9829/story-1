package com.mphasis.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.mphasis.domain.Product;

public interface ProductDAO {

	//For creating new products
	int create(Product product) throws SQLException;

	//For reading all products
	List<Product> read() throws SQLException, ParseException;

	Product read(String id) throws SQLException, ParseException;

	int update(Product product) throws SQLException;

	//For deleting the products using procedures
	void deleteProduct(String id) throws SQLException;

	//Delete using crud operation
	int delete(String id) throws SQLException;

}