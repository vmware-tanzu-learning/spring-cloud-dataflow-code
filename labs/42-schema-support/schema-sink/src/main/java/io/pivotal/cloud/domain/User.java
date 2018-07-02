package io.pivotal.cloud.domain;

/**
 * Simple class representing a User. This sink receives input messages each
 * containing an {@link Employee}. Every Employee has an associated User.
 */
public class User {

	private int id;
	private String name;
	private String email;

	public User() {
	}

	public User(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	/**
	 * This is required by the Avro message converter when recreating an instance
	 * from an incoming message. It is protected since the id should never be
	 * changed once set.
	 * 
	 * @param id
	 *            The user's internal id.
	 */
	protected void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", email=" + email + "]";
	}
}
