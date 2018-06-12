package io.pivotal.education.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * TODO-01: To speed things along, we have created a project for you to
 * complete. The next two TO-DO steps get you to review what we have done for
 * you.
 * <p>
 * TODO-02: Quickly look at the <tt>pom.xml</tt>. Note the use of the Spring
 * Boot starter for JPA, Spring Batch and Spring Batch Tests.
 * <p>
 * TODO-03a: Note that we have annotated this class to look for the Person
 * entity using <tt>@EntityScan</tt>. Take a quick look at the Person class now.
 * <p>
 * TODO-04: Time for you to do the rest. Annotate this class to enable Spring
 * Boot's support for Spring Batch.
 */
@SpringBootApplication
@EnableBatchProcessing
@EntityScan("io.pivotal.education.domain")
public class BatchApplication {

	/**
	 * The number of lines in <tt>src/main/resources/sample-data.csv</tt> - one per
	 * person.
	 */
	public static final int RECORDS_EXPECTED = 5;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BatchApplication.class, args);
		checkResults(context.getBean(JdbcTemplate.class));
	}

	/**
	 * Checks the in-memory test database (H2) contains the expected number of
	 * Person records.
	 * 
	 * @param jdbcTemplate
	 *            A JdbcTemplate for checking the PEOPLE table contents.
	 */
	public static void checkResults(JdbcTemplate jdbcTemplate) {
		final Logger logger = LoggerFactory.getLogger(BatchApplication.class);

		// Check the application worked
		Long creationCount = jdbcTemplate.queryForObject("SELECT count(*) FROM People", Long.class);

		logger.info("Finished. Application loaded " + creationCount + " people");

		if (creationCount != RECORDS_EXPECTED)
			throw new IllegalStateException("FAIL: Five Person records should have been created");
		else
			logger.info("Congratulations, application SUCCEEDED");

	}
}
