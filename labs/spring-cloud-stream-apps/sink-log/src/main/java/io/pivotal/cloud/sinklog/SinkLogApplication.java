package io.pivotal.cloud.sinklog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableConfigurationProperties(SinkLogProperties.class)
@EnableBinding(Sink.class)
public class SinkLogApplication {

  private final Logger log = LoggerFactory.getLogger(SinkLogApplication.class);

  private final SinkLogProperties props;

  public SinkLogApplication(SinkLogProperties props) {
    this.props = props;
  }

  @StreamListener(Sink.INPUT)
  public void sinkLog(String message){
    log.info(props.getPrefix() + message + props.getSuffix());
  }

  public static void main(String[] args) {
    SpringApplication.run(SinkLogApplication.class, args);
  }
}

