package io.pivotal.cloud.sinklog;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class SinkLogUnitTests {

	@Rule
	public OutputCapture capture = new OutputCapture();

	@Test
	public void simpleAppTest() {
		SinkLogProperties properties = new SinkLogProperties();
		properties.setPrefix("@");
		properties.setSuffix("@");

		SinkLogApplication app = new SinkLogApplication(properties);

		app.sinkLog("Hello Sink Log!!");;

		assertThat(capture.toString()).contains("@Hello Sink Log!!@");
	}

}
