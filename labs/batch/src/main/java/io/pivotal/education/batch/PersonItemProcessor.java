package io.pivotal.education.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import io.pivotal.education.domain.Person;

/**
 * Custom Item Processor converting all names to upper case.
 */
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

	private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

	@Override
	public Person process(final Person person) throws Exception {
		// TODO-07b: Rewrite this line to create a transformedPerson from the incoming
		// person, forcing the full name to upper-case
		final Person transformedPerson = null; // This should not be null

		log.info("Converting (" + person + ") into (" + transformedPerson + ")");
		return transformedPerson;
	}

}