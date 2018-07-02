package io.pivotal.scdf.si.simpleflow;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.MessageChannel;


public class SimpleFlowConfig {

    @Bean
    public MessageChannel input(){
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow startFlow() {
        return IntegrationFlows.from("input")
                .transform(String.class, String::toUpperCase)
                .log(LoggingHandler.Level.INFO)
                .get();
    }
}
