package com.example.demo;

import com.example.demo.Entity.Clients;
import com.example.demo.Entity.Products;
import com.example.demo.patterns.StockObserver;
import com.example.demo.patterns.StockProduct;
import com.example.demo.testing.Cifru;
import com.example.demo.testing.Complexity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		//SpringApplication.run(DemoApplication.class, args);
		//Cifru cifru = new Cifru("Ana");
		//System.out.println(cifru.check(Complexity.COMPLEX));

		StockObserver client = new Clients(15,"Ana","ioj@gxus","1569","Cluj", false);
		StockObserver client1 = new Clients(19,"Amalia","io@mmus","15333333","Cluj", false);

		Products pro1 = new Products();

		pro1.registerObserver(client);
		pro1.registerObserver(client1);

		pro1.setStock("Ciocolata", 50);
		pro1.removeObserver(client);
		pro1.setStock("Cereale", 60);


	}

}
