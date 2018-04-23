package io.pivotal.cloud.sinklog;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sink.log")
public class SinkLogProperties {

  /**
   * The prefix to use when logging messages
   */
  private String prefix = ">>> ";

  /**
   * The suffix to use when logging messages
   */
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
