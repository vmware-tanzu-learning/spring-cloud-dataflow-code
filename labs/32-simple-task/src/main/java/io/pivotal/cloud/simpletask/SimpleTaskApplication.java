package io.pivotal.cloud.simpletask;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

/**
 * A Spring Boot application that defines a task.
 */
@SpringBootApplication
@EnableTask
public class SimpleTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleTaskApplication.class, args);
	}

	/**
	 * Spring Boot automatically invokes the <tt>run()</tt> method of any
	 * CommandLineRunner beans in the Application Context immediately it has
	 * finished running its auto-configuration.
	 * 
	 * @return A {@link CommandLineRunner} that performs a trivial task.
	 */
	@Bean
	public CommandLineRunner commandLineRunner() {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				// This is our task - it just writes to the console
				// A real task would actually do something!
				System.out.println(" >>> Executing simple task");
			}
		};
	}
}
