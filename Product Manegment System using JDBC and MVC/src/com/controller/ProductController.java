package com.controller;

import java.util.Scanner;
import java.sql.*;

import com.Model.Product;
import com.mysql.cj.jdbc.Driver;

public class ProductController {
	Scanner sc = new Scanner(System.in);

	public static Connection Connection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_management", "root", "0808");
		return con;

	}

	public void Addproducts() throws ClassNotFoundException, SQLException {
		Product product = new Product();
		System.out.println(" Add products");
		System.out.println("Enter id, name,price,quantity,category");
		product.setId(sc.nextInt());
		product.setName(sc.next());
		product.setPrice(sc.nextDouble());
		product.setQuantity(sc.nextInt());
		product.setCategory(sc.next());

		int id = product.getId();
		String name = product.getName();
		double price = product.getPrice();
		int quantity = product.getQuantity();
		String category = product.getCategory();

		Statement stm = Connection().createStatement();
		String query = " insert into products values('" + id + "','" + name + "','" + price + "','" + quantity + "','"
				+ category + "')";
		stm.execute(query);
		System.out.println("Data saved Successfully");
		System.out.println("-------------------------------------------------------------------------");

	}

	public void Viweproducts() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_management", "root", "0808");
		CallableStatement cs = con.prepareCall("{call selectData()}");
		ResultSet rs = cs.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getDouble(3));
			System.out.println(rs.getInt(4));
			System.out.println(rs.getString(5));
			System.out.println("-------------------------------------------------");

		}

	}

	public void updateProducts() throws SQLException, ClassNotFoundException {

		ProductController.Connection();
		System.out.println("Avaialable Product Details :-");
		Viweproducts();

		String sql = "Select * from products";

		System.out.println("Enter Update ID");
		int updateID=sc.nextInt();
		Statement smt = Connection().createStatement();
		ResultSet rs = smt.executeQuery(sql);
		while (rs.next()) {
			if (rs.getInt(1) == updateID) {
				boolean b = true;
				while (b) {
					
					System.out.println("please Select Which Data u want to update");
					System.out.println("\t(A) name\n" + "\t (B)price\n" + "\t (C)quantity\n");
					System.out.println("Enter your choice");
					String str = sc.next();

					String updatename;
					double price;
					int quantity;

					switch (str) {
					case "A":
						System.out.println("Enter name to update");
						updatename1 = sc.next();
						
						String sql1 = "update products set name ='" + updatename1 + "' where id='" + updateID + "'";
						smt = Connection().createStatement();
						smt.execute(sql1);
						Connection().close();
						System.out.println("name updated successfully");
						break;

					case "B":
						System.out.println("enter price to update :");
						price = sc.nextDouble();
						String sql2 = "update products set price ='" + price + "' where id='" + updateID + "'";
						smt = Connection().createStatement();
						smt.execute(sql2);
						Connection().close();
						System.out.println("price updated successfully");
						break;

					case "C":
						System.out.println("enter quantity to update");
						quantity = sc.nextInt();
						String sql3 = "update products set quantity ='" + quantity + "' where id='" + updateID + "'";
						smt = Connection().createStatement();
						smt.execute(sql3);
						Connection().close();
						System.out.println("quantity updated successfully");
						break;

					}
					break;
				}
			}
		}
	}

	public void DeleteProduct() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_management", "root", "0808");
		CallableStatement cs = con.prepareCall("{call deletetData(?)}");
		System.out.println("enter id");
		cs.setInt(1, sc.nextInt());
		cs.execute();
		System.out.println("delete Data");

	}

}
