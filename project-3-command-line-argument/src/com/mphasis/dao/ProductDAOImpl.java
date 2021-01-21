package com.mphasis.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.mphasis.dbutil.MyConnection;
import com.mphasis.domain.Product;



public class ProductDAOImpl implements ProductDAO {
  
	//For autogenerated id
	private String autoGenerateId() throws SQLException
	{
		Connection con=MyConnection.getConnection();
		ResultSet rs = con.createStatement().executeQuery("select max(substr(id,2,4)) from products");
		String id="";
		if(rs.next())
		{
			int max = rs.getInt(1);
			max++;
			if(max<10)
						
				id="P00"+max;			//single digit
			else if(max<100)
				id="P0"+max;			//2 digit number
			else
				id="P"+max;				//3 digit number
		}
		return id;
	}
	
//For creating new products
	@Override
	public int create(Product product) throws SQLException
	{
		Connection con = MyConnection.getConnection();
		String str="getId";
		str=str.toLowerCase();
		boolean x = str.contains("id");
		PreparedStatement st = con.prepareStatement("INSERT INTO Products VALUES(?,?,?)");
		st.setString(1, autoGenerateId());
		st.setString(2, product.getName());
		st.setInt(3, product.getPrice());
		int no=st.executeUpdate();
		con.close();
		return no;
	}
	
	
//For reading all products
	@Override
	public List<Product> read() throws SQLException, ParseException
	{
		Connection con=MyConnection.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM Products");
		ResultSet rs = st.executeQuery();
		List<Product> productList=new ArrayList<Product>();
		while(rs.next())
		{
			Product product=new Product(rs.getString(1), rs.getString(2), rs.getInt(3));
			productList.add(product);
		}
		con.close();
		return productList;
	}

	
	@Override
	public Product read(String id) throws SQLException, ParseException
	{
		Connection con=MyConnection.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM Products WHERE id=?");
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		Product product=null;

		if(rs.next())
			product=new Product(rs.getString(1), rs.getString(2), rs.getInt(3));
		con.close();
		return product;
	}
	
	@Override
	public int update(Product product) throws SQLException
	{
		Connection con = MyConnection.getConnection();
		PreparedStatement st=null;
		st = con.prepareStatement("UPDATE Products SET name=?, price=? WHERE id=?");
		st.setString(1, product.getName());
		st.setInt(2, product.getPrice());
		st.setString(3, product.getId());
	
	int no=st.executeUpdate();
	con.close();
	return no;
	}
	
//For deleting the products using procedures
	@Override
	public void deleteProduct(String id) throws SQLException
    {
		Connection con = MyConnection.getConnection();
//        Driver driver=new oracle.jdbc.OracleDriver();
//        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE","sys as sysdba","password");
        CallableStatement st = con.prepareCall("{call prcDeleteProduct(?)}");
        st.setString(1, id);
        boolean status = st.execute();
        System.out.println(status);
        con.close();
    }

	//Delete using crud operation
	@Override
	public int delete(String id) throws SQLException
	{
		Connection con = MyConnection.getConnection();
		PreparedStatement st = con.prepareStatement("DELETE FROM Products WHERE id=?");
		
		st.setString(1, id);
		int no=st.executeUpdate();
		con.close();
		return no;
	}
}