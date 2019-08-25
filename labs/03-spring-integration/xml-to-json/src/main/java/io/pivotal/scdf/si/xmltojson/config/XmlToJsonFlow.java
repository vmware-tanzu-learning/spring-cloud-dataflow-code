package io.pivotal.scdf.si.xmltojson.config;

import io.pivotal.scdf.si.xmltojson.domain.Order;
import io.pivotal.scdf.si.xmltojson.repository.ItemRepository;
import io.pivotal.scdf.si.xmltojson.service.ItemPriceFixer;
import io.pivotal.scdf.si.xmltojson.utils.OrderDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.dsl.DirectChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.handler.LoggingHandler;

import java.io.File;

public class XmlToJsonFlow {

    @Bean
    public IntegrationFlow flow() throws Exception{
        return IntegrationFlows

                .from(Files.inboundAdapter(new File(System.getProperty("user.home") + "/Desktop/input"))
                        .autoCreateDirectory(true)
                        .patternFilter("*.xml"),e -> e.poller(Pollers.fixedDelay(5000L)))
                .transform(Files.toStringTransformer("UTF8",true))
                .<String,Order>transform(source -> {
                    return new OrderDeserializer().deserialize(source);
                })
                .channel("orginalOrder")
                .split(Order.class,Order::getItemList)
                .routeToRecipients(orderItem -> orderItem
                        .recipient("drinks","payload.getType().equals('drink')")
                        .recipient("food","payload.getType().equals('food')"))
                .get();
    }

    @Bean
    public DirectChannelSpec orginalOrder() {
        return MessageChannels
                .direct()
                .wireTap("loggingFlow.input");
    }

    @Bean
    public IntegrationFlow loggingFlow() {
        return f -> f.log(LoggingHandler.Level.INFO);
    }

    @Bean
    ItemRepository repository(){
        return new ItemRepository();
    }

    @Bean
    public ItemPriceFixer itemPriceFixer(ItemRepository repository){
        return new ItemPriceFixer(repository);
    }

    @Bean
    public IntegrationFlow drinksFlow() throws Exception {
        return IntegrationFlows
                .from("drinks")
                .handle("itemPriceFixer","process")
                .transform(Transformers.toJson())
                .log(LoggingHandler.Level.INFO)
                .get();
    }

    @Bean
    public IntegrationFlow foodFlow() throws Exception {
        return IntegrationFlows
                .from("food")
                .handle("itemPriceFixer","process")
                .transform(Transformers.toJson())
                .log(LoggingHandler.Level.INFO)
                .get();
    }
}