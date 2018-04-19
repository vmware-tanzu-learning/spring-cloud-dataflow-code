package io.pivotal.cloud.sourcetime;

import java.util.Locale;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="source.time")
public class SourceTimeProperties {

  private String format = "MM/dd/yyyy HH:mm:ss a";
  private String locale = "en_US";

  public String getFormat() {
    return format;
  }
  public void setFormat(String format) {
    this.format = format;
  }
  public String getLocale() {
    return locale;
  }
  public void setLocale(String locale) {
    this.locale = locale;
  }

  public Locale getLocaleFromString(){
    String[] data = locale.split("_");
    return new Locale(data[0],data[1]);
  }
}
