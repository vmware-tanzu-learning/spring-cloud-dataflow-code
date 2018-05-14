package io.pivotal.scdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.DateFormat;
import java.util.Date;

@EnableScheduling
@SpringBootApplication
public class SourceSinkDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceSinkDemoApplication.class, args);
    }

    // Client
    @Autowired
    RabbitTemplate template;

    @Bean
    public Queue demo(){
        return new Queue("demo",false,false,true);
    }

    @Scheduled(fixedRate = 1000)
    public void sender(){
        template.convertAndSend("demo",String.format("Hello at %s", DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date())));
    }

}


@EnableBinding(Source.class)
class DemoSource {

    @Bean
    public IntegrationFlow amqpInbound(ConnectionFactory connectionFactory) {
        return IntegrationFlows.from(Amqp.inboundAdapter(connectionFactory, "demo"))
                .channel(Source.OUTPUT)
                .get();
    }
}


@EnableBinding(Sink.class)
class DemoLog {

    private static Logger log = LoggerFactory.getLogger(DemoLog.class);

    @StreamListener(Sink.INPUT)
    public void handler(String message){
        log.info(message);
    }
}
