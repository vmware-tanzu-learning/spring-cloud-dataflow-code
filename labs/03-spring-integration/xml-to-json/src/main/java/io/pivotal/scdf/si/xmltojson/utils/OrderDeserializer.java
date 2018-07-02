package io.pivotal.scdf.si.xmltojson.utils;

import com.thoughtworks.xstream.XStream;
import io.pivotal.scdf.si.xmltojson.domain.Order;
import io.pivotal.scdf.si.xmltojson.domain.OrderItem;
import org.springframework.core.serializer.Deserializer;

import java.io.IOException;
import java.io.InputStream;

public class OrderDeserializer  implements Deserializer<Order> {

    private XStream xstream = new XStream();

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
