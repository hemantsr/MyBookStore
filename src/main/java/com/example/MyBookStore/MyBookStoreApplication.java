package com.example.MyBookStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MyBookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBookStoreApplication.class, args);
	}

}
