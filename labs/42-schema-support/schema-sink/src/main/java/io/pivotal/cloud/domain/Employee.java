package io.pivotal.cloud.domain;

/**
 * Simple class representing a User. This sink receives input messages each
 * containing an {@link Employee}.
 */
public class Employee {

	private User user;
	private String message;
	private String employeeId;

	public Employee() {
	}

	public Employee(User user, String message, String employeeId) {

		this.user = user;
		this.message = message;
		this.employeeId = employeeId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "Employee [user=" + user + ", message=" + message + ", employeeId=" + employeeId + "]";
	}
}