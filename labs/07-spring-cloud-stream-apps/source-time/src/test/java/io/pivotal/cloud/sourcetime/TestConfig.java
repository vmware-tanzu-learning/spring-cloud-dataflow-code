package io.pivotal.cloud.sourcetime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.scheduling.support.PeriodicTrigger;

@Configuration
public class TestConfig {
  @Bean(name = PollerMetadata.DEFAULT_POLLER )
  public PollerMetadata defaultPoller() {
    PollerMetadata pollerMetadata = new PollerMetadata();
    pollerMetadata.setTrigger(new PeriodicTrigger(1));
    pollerMetadata.setMaxMessagesPerPoll(1);
    return pollerMetadata;
  }
}