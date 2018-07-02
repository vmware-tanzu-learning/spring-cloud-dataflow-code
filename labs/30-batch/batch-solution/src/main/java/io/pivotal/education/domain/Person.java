package io.pivotal.education.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Example domain class - a representation of a Person. Each instance
 * corresponds to a row in the PEOPLE table.
 */
@Entity
@Table(name = "PEOPLE")
public class Person {

	@Id
	@GeneratedValue
	private long id;

	private String lastName;
	private String firstName;

	// For JPA and Spring Batch BeanWrapperFieldMapper
	public Person() {
	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "firstName: " + firstName + ", lastName: " + lastName;
	}

}
