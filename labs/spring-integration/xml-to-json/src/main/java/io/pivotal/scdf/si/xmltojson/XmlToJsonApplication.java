package io.pivotal.scdf.si.xmltojson;

import com.thoughtworks.xstream.XStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.serializer.Deserializer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.dsl.channel.DirectChannelSpec;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.handler.LoggingHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@ImportResource("META-INF/integration/orders-context.xml")
public class XmlToJsonApplication {

	public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(XmlToJsonApplication.class, args);
        System.out.println("Hit 'Enter' to terminate");
        System.in.read();
        ctx.close();
	}
}


//@Configuration
class XmlToJsonFlow {


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

    @Bean ItemRepository repository(){
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

class ItemPriceFixer {

    ItemRepository repo;
    public ItemPriceFixer(ItemRepository repo){
        this.repo = repo;
    }

    public OrderItem process(OrderItem orderItem){
        Item item = this.repo.findById(orderItem.getId());
        orderItem.setUnitPrice(item.getPrice());
        orderItem.setDescription(item.getDescription());
        return orderItem;
    }
}

class ItemRepository {

    private List<Item> items = Arrays.asList(new Item("0321200683",6.99F,"Sushi Combo Platter"),new Item("1590596439",4.55F,"Sake"),new Item("1590596440",3.25F,"Red Wine Glass"));

    public Item findById(String id){
        Optional<Item> item = items.stream().filter(unit -> unit.getId().equals(id)).findFirst();
        if(item.isPresent())
            return item.get();
        return new Item();

    }

}

class Item {
    private String id;
    private Float price = -1F;
    private String description = "WRONG ITEM";

    public Item() {
    }

    public Item(String id, Float price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

class OrderDeserializer implements Deserializer<Order> {

    XStream xstream = new XStream();

    public OrderDeserializer(){
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[] { Order.class, OrderItem.class });
        xstream.alias("order", Order.class);
        xstream.alias("orderItem", OrderItem.class);
        xstream.addImplicitCollection(Order.class, "itemList");
    }


    @Override
    public Order deserialize(InputStream inputStream) throws IOException {
        return (Order)xstream.fromXML(inputStream);
    }

    public Order deserialize(String inputStream){
        return (Order)xstream.fromXML(inputStream);
    }
}


class Order {

    private List<OrderItem> itemList;

    public Order() {}

    public Order(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "itemList=" + itemList +
                '}';
    }
}


class OrderItem {
    private String id;
    private String type;
    private Integer quantity;
    private Float unitPrice;
    private String description;

    public OrderItem(){}

    public OrderItem(String id, String type, Integer quantity) {
        this.id = id;
        this.type = type;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", description='" + description + '\'' +
                '}';
    }
}