package io.pivotal.cloud.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.pivotal.cloud.domain.Employee;
import io.pivotal.cloud.domain.User;

/**
 * A mock service that converts each User registered with it into an Employee
 * with a randomly generated details field.
 */
@Service
public class EmployeeService {

	private Random rand = new Random(System.currentTimeMillis());

	private static final String[] DETAILS = { "Just started at Pivotal!", //
			"My check is in the mail", //
			"Welcome to my Avro moment!", //
			"Nothing to see here, come back yesterday!", //
			"I've been promoted!!", //
			"I can't afford an iPhone X!" };

	private static final int NUM_DETAILS = DETAILS.length;

	public Employee registerEmployee(User user) {
		return new Employee(user, getNextMessage(), UUID.randomUUID().toString());
	}

	protected String getNextMessage() {
		return DETAILS[rand.nextInt(NUM_DETAILS)];
	}
}