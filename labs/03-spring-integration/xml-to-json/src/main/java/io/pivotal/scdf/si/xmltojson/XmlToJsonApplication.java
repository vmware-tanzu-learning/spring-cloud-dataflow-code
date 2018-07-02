package io.pivotal.scdf.si.xmltojson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

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
