package io.kraluk.orderprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class OrderProcessorApplication {

  public static void main(final String[] args) {
    SpringApplication.run(OrderProcessorApplication.class, args);
  }
}
