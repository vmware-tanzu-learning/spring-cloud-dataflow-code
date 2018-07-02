package io.pivotal.education.batch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BatchApplicationTests {

	/**
	 * This default test just checks that your Spring configuration will load. It is
	 * automatically created for any project built using the Spring Initializr.
	 */
	@Test
	public void test1ContextLoads() {
	}

	// Spring Batch provides these three
	@Autowired
	private Job job;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private JobLauncher jobLauncher;

	// Spring Batch Test utility class
	private JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();

	// Spring Boot provides this
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setup() {
		jobLauncherTestUtils.setJob(job);
		jobLauncherTestUtils.setJobRepository(jobRepository);
		jobLauncherTestUtils.setJobLauncher(jobLauncher);

		// When running multiple tests, they share the same database. Need to empty the
		// PEOPLE table before each test.
		jdbcTemplate.execute("DELETE FROM People");
	}

	/**
	 * TODO-11: This is the typical example of testing that a Spring Batch job can
	 * runs successfully. Recall that spring-batch-tests dependency was in the Maven
	 * POM. Run the tests in this file - they should pass. You should see the 5
	 * users listed on the console log output with their names in upper case.
	 */
	@Test
	public void myBatchJobRuns() {
		// Run the job - assumes there is only one Job in the Spring configuration
		try {
			jobLauncherTestUtils.launchJob();

			BatchApplication.checkResults(jdbcTemplate);
		} catch (Exception e) {
			Assert.fail("Job failed: " + e);
		}
	}
}
