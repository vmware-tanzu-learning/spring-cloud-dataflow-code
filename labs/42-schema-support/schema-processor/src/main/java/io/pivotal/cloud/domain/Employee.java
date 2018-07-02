package io.pivotal.cloud.domain;

/**
 * Simple class representing an Employee. The job of this Processor application
 * is to register each {@link User} it receives as a new Employee and then pass
 * on that Employee to the next step in the Flow (the Sink application).
 */
public class Employee {

	private User user;
	private String details;
	private String employeeId;

	public Employee() {
	}

	public Employee(User user, String details, String employeeId) {

		this.user = user;
		this.details = details;
		this.employeeId = employeeId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String message) {
		this.details = message;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "Employee [user=" + user + ", details=" + details + ", employeeId=" + employeeId + "]";
	}
}