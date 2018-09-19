package io.pivotal.cloud.sourcetime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectMessagesTests {

  @Autowired
  private Source timeSource;

  @Autowired
  private MessageCollector messageCollector;


  @Test
  public void sourceTimeTest() throws InterruptedException {
    Thread.sleep(5000);
    assertThat(messageCollector.forChannel(timeSource.output()).size()).isGreaterThan(4);
  }

}
