package io.pivotal.cloud.sourcetime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetMessageTests {

  private final Logger log = LoggerFactory.getLogger(GetMessageTests.class);

  @Autowired
  private MessageSource<String> sourceTime;

  @Test
  public void contextLoads() {
    assertNotNull(sourceTime);
  }

  @Test
  public void simpleSourceTimeTest() {
    Message<String> message = sourceTime.receive();
    assertNotNull(message);
    log.info(message.getPayload());
  }

}
