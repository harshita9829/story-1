package com.mphasis.app;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import com.mphasis.dao.ProductDAO;
import com.mphasis.dao.ProductDAOImpl;
import com.mphasis.domain.Product;

public class Main_Product {

	public static void main(String[] args) throws SQLException, ParseException {

		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		ProductDAO pdao = new ProductDAOImpl();
		Product p = new Product();
//        String name = "";
//        int price = 0;
		System.out.println("A. View Products \r\n"+ "B. Add Products \r\n"+
		"C. Update Product\r\n"+"D. Delete Product \r\n"+"E. Search Product \r\n"+"Exit");	
		System.out.println("Choose the option");
		char option=sc.next().charAt(0); 
		
		switch(option) {
		case 'A':{
			System.out.println("The products details are");
			System.out.println(pdao.read());
			break;
		
		}
		
	    case 'B':{
		   System.out.println("Add the product");
		  
		   System.out.println("Enter the name");
		   String name = sc1.nextLine();
		   p.setName(name);
		   System.out.println("Enter the price");
		   int price = sc.nextInt();
		   p.setPrice(price);
		   pdao.create(p);
		   System.out.println("Product added successfully");
		   break;
	    }
	
	    case 'C':{
			   System.out.println("Update the Product");
			   System.out.println("Enter the id of the product to be updated");
			   String id = sc1.nextLine();
			   p.setId(id);
			   System.out.println("New product name");
			   String name = sc1.nextLine();
			   p.setName(name);
			   System.out.println("New product price");
			   int price = sc.nextInt();
			   p.setPrice(price);
			   pdao.update(p);
			   System.out.println("Product details updated successfully");
			   break;
		    }
	    
	    case 'D':{
			   System.out.println("Delete the Product");
			   System.out.println("Enter the product id");
			   String id = sc1.nextLine();
			   System.out.println(pdao.delete(id));
			   System.out.println("Product deleted successfully");
			   break;
		    }
	    
	   case 'E':{
			   System.out.println("Search the product by id");
			   System.out.println("Enter the product id");
			   String id = sc1.nextLine();
			   System.out.println(pdao.read(id));
			   break;
		    }
	   default:
		   System.out.println("Exit");
		}
		
		
	}

}
