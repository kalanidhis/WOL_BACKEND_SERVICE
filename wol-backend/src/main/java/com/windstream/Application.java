package com.windstream;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Created by sachi on 11/28/2016.
 */
@SpringBootApplication
public class Application
{

	static final Logger logger = LogManager.getLogger(Application.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		
	}
}
