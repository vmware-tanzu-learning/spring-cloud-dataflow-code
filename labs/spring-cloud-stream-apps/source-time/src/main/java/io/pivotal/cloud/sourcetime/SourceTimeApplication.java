package io.pivotal.cloud.sourcetime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableConfigurationProperties(SourceTimeProperties.class)
@EnableBinding(Source.class)
public class SourceTimeApplication {

  public SourceTimeApplication(SourceTimeProperties props) {
    this.props = props;
  }

  private final SourceTimeProperties props;

  @Bean
  @InboundChannelAdapter(Source.OUTPUT)
  public MessageSource<String> sourceTime() {
    return () ->
        new GenericMessage<>(
            new SimpleDateFormat(
                props.getFormat(),
                props.getLocaleFromString()).format(new Date()));
  }

  public static void main(String[] args) {
    SpringApplication.run(SourceTimeApplication.class, args);
  }

}
