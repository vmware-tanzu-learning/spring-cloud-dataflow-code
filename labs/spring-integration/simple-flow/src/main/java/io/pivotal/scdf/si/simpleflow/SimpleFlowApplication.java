package io.pivotal.scdf.si.simpleflow;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
@ImportResource("META-INF/integration/simple-flow.xml")
public class SimpleFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleFlowApplication.class, args);
	}

	@Bean
    public CommandLineRunner process(MessageChannel input){
	    return args -> {

	        input.send(MessageBuilder.withPayload("spring integration rocks!").build());

        };
    }
}


//@Configuration
class SimpleFlow {

    @Bean
    public MessageChannel input(){
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow startFlow() {
        return IntegrationFlows.from("input")
                .transform(String.class, String::toUpperCase)
                .handle(System.out::println)
                .get();
    }


}