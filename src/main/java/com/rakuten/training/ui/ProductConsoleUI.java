package com.rakuten.training.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;

@Component("uiObj")
public class ProductConsoleUI {

	ProductService service; // = new ProductServiceImpl();
	@Autowired
	public void setService(ProductService service) {
		this.service = service;
	}

	public void createProductWithUI() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Name: ");
		String name = s.nextLine();
		System.out.println("Enter price: ");
		float price = Float.parseFloat(s.nextLine());
		System.out.println("Enter Qoh: ");
		int qoh = Integer.parseInt(s.nextLine());

		Product p = new Product(name, price, qoh);
		int id = service.addNewProduct(p);
		System.out.println("Created Product with Id: " + id);
	}

}
