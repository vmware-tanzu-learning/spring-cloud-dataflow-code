package io.pivotal.cloud.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import io.pivotal.cloud.domain.User;

/**
 * Mock repository that generates a random user each time it is invoked.
 */
@Repository
public class UserRepository {

	private final String[] userNames = { //
			"Sean", "Ayala", "Lee", "Ivan", "Ida", "Nadia", "Arjun", "Yu Yan", "Carlos", "Julia", "Suzie" };

	private final int NUM_USERS = userNames.length;
	private final Logger logger = LoggerFactory.getLogger(UserRepository.class);

	/**
	 * Fetch a user by id.
	 * 
	 * @param id
	 *            Internal user id
	 * @return A mock user.
	 */
	public User getUser(int id) {
		String name = userNames[id % NUM_USERS];
		User user = new User(id, name, name.toLowerCase() + "@example.com");
		logger.info(" >>> SEND {}", user);
		return user;
	}
}