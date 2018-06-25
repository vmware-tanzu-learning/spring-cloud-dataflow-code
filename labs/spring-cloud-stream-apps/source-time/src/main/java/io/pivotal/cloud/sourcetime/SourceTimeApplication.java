package io.pivotal.cloud.sourcetime;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

/**
 * This is Spring Boot application. The annotations are:
 * <ul>
 * <li>@SpringBootApplication - ensure Spring Boot auto-configuration
 * <li>@PropertySource - read our custom properties file
 * <li>@EnableConfigurationProperties - create an instance of the specified
 * class ({@link SourceTimeProperties}) and initialize it from its properties.
 * <li>@EnableBinding - bind this application as a Source. A
 * spring.cloud.stream.bindings.output.destination</tt> property is expected to
 * define where the output goes.
 */
@SpringBootApplication
@PropertySource("classpath:/source-time.properties")
@EnableConfigurationProperties(SourceTimeProperties.class)
@EnableBinding(Source.class)
public class SourceTimeApplication {

	// Timestamp configuration properties.
	private final SourceTimeProperties props;

	/**
	 * Automatically called by Spring (no need for <tt>@Autowired</tt>).
	 * 
	 * @param props
	 *            Timestamp configuration properties.
	 */
	public SourceTimeApplication(SourceTimeProperties props) {
		this.props = props;
	}

	/**
	 * As this is an InboundAdapter is it generating messages to send to its
	 * channel.
	 * 
	 * @return Messages to send to its channel. Every time it is invoked, it
	 *         generates a new message.
	 */
	@Bean
	@InboundChannelAdapter(channel = Source.OUTPUT)
	public MessageSource<String> sourceTime() {
		return () -> new GenericMessage<>(
				new SimpleDateFormat(props.getFormat(), props.getLocaleFromString()).format(new Date()));
	}

	/**
	 * Run this application as a Spring Boot application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SourceTimeApplication.class, args);
	}

}