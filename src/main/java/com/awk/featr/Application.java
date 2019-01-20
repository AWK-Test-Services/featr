package com.awk.featr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com/awk/featr/services", "com/awk/featr/controllers"})
public class Application {

	public static void main(String[] args) {
	    SpringApplication.run(Application.class, args);
	}
}