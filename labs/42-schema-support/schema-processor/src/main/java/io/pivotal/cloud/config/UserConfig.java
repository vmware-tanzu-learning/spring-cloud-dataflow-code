package io.pivotal.cloud.config;

import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeType;

/**
 * Configuration to create the Avro message converter. This converts Java
 * objects to/from Avro binary format messages. For more details see:
 * <ul>
 * <li>https://en.wikipedia.org/wiki/Apache_Avro
 * <li>https://avro.apache.org/
 * </ul>
 * 
 * This class is the SAME in all three schema projects.
 */
@Configuration
public class UserConfig {

	@Bean
	public MessageConverter messageConverter() {
		return new AvroSchemaMessageConverter(MimeType.valueOf("avro/bytes")); //
	}
}