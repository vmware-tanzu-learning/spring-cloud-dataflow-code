package io.pivotal.cloud.sinklog;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SinkLogIntegrationTests {

	@Rule
	public OutputCapture capture = new OutputCapture();

	@Autowired
	private Sink sink;

	@Test
	public void sinkTest() {
		assertNotNull(sink.input());

		GenericMessage<String> message = new GenericMessage<>("Using Sink input channel");

		this.sink.input().send(message);

		assertThat(capture.toString()).contains("[Using Sink input channel]");
	}

}
