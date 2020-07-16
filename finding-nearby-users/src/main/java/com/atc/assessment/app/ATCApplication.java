package com.atc.assessment.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Marappan Sampath
 * 
 *         Excluding SecurityAutoConfiguration class to avoid automatic password
 *         generation by spring security API. Excluding
 *         HibernateJpaAutoConfiguration, DataSourceAutoConfiguration to avoid checking
 *         data source configuration at startup
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@ComponentScan("com.atc.assessment")
//@EntityScan( basePackages = {"com.atc.assessment"}) //commenting hence specifying in properties file
public class ATCApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ATCApplication.class, args);
	}
}
