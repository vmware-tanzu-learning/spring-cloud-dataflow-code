package io.pivotal.cloud.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.SendTo;

import io.pivotal.cloud.config.UserConfig;
import io.pivotal.cloud.domain.Employee;
import io.pivotal.cloud.domain.User;
import io.pivotal.cloud.service.EmployeeService;

/**
 * This is the main component of this application. It performs the Processor
 * role, accepting a User as input and sending a derived Employee as its output.
 * <p>
 * As this is registered as a Processor, Spring will automatically create an
 * instance as a Spring Bean.
 */
@EnableBinding(Processor.class)
public class UserProcessor {

	private final Logger logger = LoggerFactory.getLogger(UserProcessor.class);
	private final EmployeeService service;

	/**
	 * Spring will automatically inject the message service when it creates an
	 * instance of this class. As it is the only constructor, <tt>@Autowired</tt> is
	 * not required.
	 * 
	 * @param service
	 *            The message service
	 */
	public UserProcessor(EmployeeService service) { //
		this.service = service;
	}

	/**
	 * This method does the actual processing - converting a User to an Employee.
	 * <p>
	 * The input and output destinations used by this processor are defined in
	 * <tt>application.properties</tt>.
	 * <p>
	 * The payload of each incoming message is deserialized from Avro binary format
	 * using the {@link MessageConverter} defined by {@link UserConfig}.
	 * <p>
	 * The same converter:
	 * <li>Takes the Employee and converts it to Avro binary format,
	 * <li>Makes that the payload of a new message
	 * <li>Sends the message to the output
	 *
	 * @param user
	 *            User instance read from the incoming message.
	 * @return The Employee instance to pass on.
	 */
	@StreamListener(Processor.INPUT) //
	@SendTo(Processor.OUTPUT)
	public Employee transform(User user) {
		// Log the incoming User. The Avro message converter automatically extracted the
		// Avro (see UserConfig class) representation from the incoming message and
		// converted it to a User object.
		logger.info(" >>> TRANSFORM {}", user);

		// Determine the Employee from the incoming User.
		Employee employee = service.registerEmployee(user);
		logger.info(" >>>  INTO {}", employee);

		// This is converted to Avro binary format and sent to the output destination.
		return employee;
	}
}