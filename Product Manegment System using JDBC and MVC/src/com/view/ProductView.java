package com.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.controller.ProductController;

public class ProductView {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		ProductController p = new ProductController();
		ProductController.Connection();

		while (true) {

			System.out.println("\n 1: Add Products \t");
			System.out.println("\n 2: viwe all Product \t");
			System.out.println("\n 3: Update a product \t");
			System.out.println("\n 4: Delete product \t");
			System.out.println("\n 5:Exits \t");
			System.out.println("====================================================");

			System.out.println("Enter your choice");
			int choice = sc.nextInt();

			switch (choice) {

			case 1:

				p.Addproducts();
				break;

			case 2:
				p.Viweproducts();
				break;

			case 3:
				p.updateProducts();
				break;

			case 4:
				p.DeleteProduct();
				break;

			case 5:
				System.exit(choice);
			}

		}

	}

}
