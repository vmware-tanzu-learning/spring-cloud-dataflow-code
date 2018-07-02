package io.pivotal.cloud.stream;

import java.util.Random;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import io.pivotal.cloud.domain.User;
import io.pivotal.cloud.repository.UserRepository;

/**
 * This is the main component of this application. It performs the Source role,
 * sending Users to its output.
 * <p>
 * As this is registered as a Source, Spring Stream will automatically create an
 * instance as a Spring Bean. <tt>@EnableBinding</tt> is itself annotated with
 * <tt>@Configuration</tt> so the {@link #sendUser(UserRepository)}
 * <tt>@Bean</tt> method will be invoked to create the MessageSource.
 */
@EnableBinding(Source.class)
public class UserSource {

	private Random rand = new Random(System.currentTimeMillis());

	@Bean
	@InboundChannelAdapter(channel = Source.OUTPUT, poller = { @Poller(fixedRate = "${user.rate:5000}") }) //
	public MessageSource<User> sendUser(UserRepository repo) {
		// This closure implements MessageSource.receive().
		// - Fetches a random user each time invoked
		// Invoked every fixedRate seconds due to the Poller
		// - user.rate is defined in application.properties
		// - if user.rate is not defined it defaults to 5000ms = 5s
		return () -> new GenericMessage<>(repo.getUser(rand.nextInt(Integer.MAX_VALUE)));
	}

}