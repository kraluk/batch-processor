package io.kraluk.orderprocessor.test;

import io.kraluk.orderprocessor.adapter.order.event.OrderEventPublisher;
import io.kraluk.orderprocessor.adapter.orderupdate.repository.OrderUpdateDownloader;
import io.kraluk.orderprocessor.test.adapter.orderupdate.repository.StaticOrderUpdateDownloader;
import io.kraluk.orderprocessor.test.db.ClearDatabaseExtension;
import io.kraluk.orderprocessor.test.event.LoggingOrderEventPublisher;
import io.kraluk.orderprocessor.test.web.TestRestClientTestConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
@Testcontainers
@Import({
    TestRestClientTestConfiguration.class,
    NoAwsTestConfiguration.class
})
@ExtendWith(ClearDatabaseExtension.class)
public abstract class IntegrationTest {
}

@TestConfiguration
class NoAwsTestConfiguration {
  private static final Logger log = LoggerFactory.getLogger(NoAwsTestConfiguration.class);

  @ConditionalOnMissingBean(OrderEventPublisher.class)
  @Bean
  OrderEventPublisher orderEventPublisher() {
    log.warn("Using logging OrderEventPublisher for tests");
    return new LoggingOrderEventPublisher();
  }

  @ConditionalOnMissingBean(OrderUpdateDownloader.class)
  @Bean
  OrderUpdateDownloader orderUpdateDownloader() {
    log.warn("Using static OrderUpdateDownloader for tests");
    return new StaticOrderUpdateDownloader();
  }
}