package io.pivotal.cloud.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import io.pivotal.cloud.domain.Employee;

/**
 * This is the main component of this application. It performs the Sink role,
 * accepting Employees from its input and logging them.
 * <p>
 * * As this is registered as a Sink:
 * <ul>
 * <li>Spring Stream will automatically create an instance as a Spring Bean.
 * <li>The <tt>@StreamListener</tt> annotation causes a listener to be run in
 * its own thread, listening on the input stream (defined in
 * <tt>application.properties</tt>) and invoking {@link #log(Employee)} for each
 * message received.
 */
@EnableBinding(Sink.class)
public class EmployeeSink {
	private final Logger logger = LoggerFactory.getLogger(EmployeeSink.class);

	@StreamListener(Sink.INPUT)
	public void log(Employee employee) {
		// Log the employee to show we received it
		logger.info("RECEIVED: {}", employee);
	}

}
