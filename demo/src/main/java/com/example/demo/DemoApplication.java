package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		//Cifru cifru = new Cifru("Ana");
		//System.out.println(cifru.check(Complexity.COMPLEX));

		/*StockObserver client = new Clients(15,"Ana","ioj@gxus","1569","Cluj", false);
		StockObserver client1 = new Clients(19,"Amalia","io@mmus","15333333","Cluj", false);
		StockObserver client2 = new Clients(25,"Maria","maria865","1569873","Cluj", false);
		StockObserver client3 = new Clients(60,"Vlad","vlad@vl.com","99853333","Cluj", true);

		Products pro1 = new Products();
		Products pro2 = new Products();

		pro1.registerObserver(client);
		pro1.registerObserver(client1);
		pro1.registerObserver(client2);

		pro2.registerObserver(client3);

		pro1.setPrice("Ciocolata", 19);
		pro2.setPrice("Biscuiti", 13);
		pro1.removeObserver(client);
		pro1.setPrice("Cereale", 60);*/


	}

}
