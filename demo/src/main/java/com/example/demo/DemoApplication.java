package com.example.demo;

import com.example.demo.testing.Cifru;
import com.example.demo.testing.Complexity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		//SpringApplication.run(DemoApplication.class, args);
		Cifru cifru = new Cifru("Ana");
		System.out.println(cifru.check(Complexity.COMPLEX));

	}

}
