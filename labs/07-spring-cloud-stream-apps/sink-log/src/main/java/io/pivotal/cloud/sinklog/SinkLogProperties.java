package io.pivotal.cloud.sinklog;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Wrapper class for our custom properties. Spring Boot will automatically create
 * an instance and initialize it from any sink.log.xxx properties it can find.
 */
@ConfigurationProperties(prefix = "sink.log")
public class SinkLogProperties {

	private String prefix = ">>> ";

	private String suffix = " <<<";

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
