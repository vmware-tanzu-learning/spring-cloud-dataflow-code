package io.pivotal.education.batch;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import io.pivotal.education.domain.Person;

/**
 * TODO-05: The entire configuration for the Batch Job will be in here. Annotate
 * this as a Spring Java configuration class.
 */
@Configuration
public class BatchConfiguration {

	/**
	 * The name of our job: {@value}.
	 */
	public static final String JOB_NAME = "importUserJob";

	/**
	 * The name of our step: {@value}. A job can have many steps, but this job has
	 * only one.
	 */
	public static final String STEP_NAME = "step1";

	/**
	 * The name of our item reader: {@value}.
	 */
	private static final String PERSON_ITEM_READER = "personItemReader";

	/**
	 * Sample input file containing names of people to process and store.
	 */
	public static final String SAMPLE_DATA_CSV = "sample-data.csv";

	// Created automatically by Spring Boot
	public JobBuilderFactory jobBuilderFactory;

	// Created automatically by Spring Boot
	public StepBuilderFactory stepBuilderFactory;

	/**
	 * Get Spring to automatically pass in the factories created by Spring Boot.
	 * 
	 * @param jobBuilderFactory
	 *            A builder for creating a new Job.
	 * @param stepBuilderFactory
	 *            A builder for creating a new Job Step.
	 */
	@Autowired
	public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		super();
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

	/**
	 * TheBatch Item Reader - reads lines from a CSV input file.
	 * 
	 * @return A flat-file item reader configured to read our CSV file.
	 */
	@Bean
	public FlatFileItemReader<Person> reader() {
		// A field-set mapper is used to set the fields of the specified target (Person)
		// (using reflection) from the data read by an Item Reader. In this case the
		// fields read from the CSV input file will be used to set the data-members
		// of a Person instance.
		FieldSetMapper<Person> fieldMapper = new BeanWrapperFieldSetMapper<Person>() {
			{
				setTargetType(Person.class);
			}
		};

		// The input file is on the classpath
		Resource inputFile = new ClassPathResource(SAMPLE_DATA_CSV);

		// TODO-06: Create a new FlatFileItemReader to read a CSV input file.
		// Set the following using the fluent API style:
		// a. Create a new FlatFileItemReaderBuilder<Person>
		// b. Set name to PERSON_ITEM_READER (see top of class)
		// c. The input resource is inputFile (see previous statement)
		// d. Call delimited() as the CSV file is comma-delimited
		// e. Set the fieldSetMapper to fieldMapper (see top of method)
		// f. Invoke build() and return what it creates
		return null; // This should not return null
	}

	/**
	 * The processor - will be applied to each line of input read. As the
	 * FlatFileItemReaderBuilder is creating Person objects, this processor will
	 * process each Person object created from each line in the CSV file.
	 * 
	 * @return The custom Item processor.
	 * @see PersonItemProcessor
	 */
	@Bean
	public PersonItemProcessor processor() {
		// TODO-07a: The PersonItemProcessor has been partly written and will be
		// returned as a Spring Bean. You need to make it do something. Edit
		// PersonItemProcessor class now.
		return new PersonItemProcessor();
	}

	/**
	 * The item writer will write the data processed (PeErson objects) to the
	 * underlying database using JPA. The Person object must be an @Entity for this
	 * to work.
	 * 
	 * @param entityManagerFactory
	 *            To use JPA we need entity-managers. This factory creates them on
	 *            demand.
	 * @return The JPA item writer.
	 */
	@Bean
	public ItemWriter<Person> personWriter(EntityManagerFactory entityManagerFactory) {
		// TODO-08: Create an instance of JpaItemWriter for <Person>, set its
		// Entity Manager Factory and return it.
		return null; // This should not return null
	}

	/**
	 * Setup the Job using the post-job listener and the specified step. A job may
	 * have many steps, this one has just one.
	 * 
	 * @param listener
	 *            The job-completion listener will be invoked once the job has
	 *            finished. There are several listeners that can be applied to both
	 *            jobs and steps. Their output can be viewed as a Stream when
	 *            running a Spring Batch task in Spring Cloud Data Flow.
	 * @param step1
	 *            The only step in this job.
	 * @return
	 */
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		// TODO-09: We have setup the Job for you. Make sure understand what this code
		// does.

		return jobBuilderFactory.get(JOB_NAME) // Get a builder for this job
				.incrementer(new RunIdIncrementer()) // Defines job ids: 1, 2, 3 ...
				.listener(listener) // Our post-job listener validates job
				.flow(step1) // The step that the job actually runs
				.end() // End of job definition
				.build(); // Build the job
	}

	/**
	 * Our job consists of a single step to read a CSV file, convert the data to
	 * Person objects, transform their names to upper case and save them in the H2
	 * database.
	 *
	 * @param reader
	 *            The Item Reader to use.
	 * @param processor
	 *            An Item Processor to convert the names
	 * @param writer
	 *            An Item Writer to save to the database
	 * @return The step.
	 */
	@Bean
	public Step step1(ItemReader<Person> reader, ItemProcessor<Person, Person> processor, ItemWriter<Person> writer) {
		// TODO-10: Use the stepBuilderFactory to
		// a. Create a step with the name STEP_NAME using get() 
		//    - code looks similar to getting the Job builder in the previous method.
		// b. Set the reader, processor and writer.
		// c. Build and return
		return null; // This should not return null
	}
}
