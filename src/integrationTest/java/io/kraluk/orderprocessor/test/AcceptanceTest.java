package io.kraluk.orderprocessor.test;

import io.kraluk.orderprocessor.adapter.orderupdate.web.OrderUpdateExecutionsWebTestClient;
import io.kraluk.orderprocessor.test.aws.AwsIntegrationTest;
import io.kraluk.orderprocessor.test.db.order.OrderDatabaseTestConfiguration;
import io.kraluk.orderprocessor.test.db.order.OrderTestDatabase;
import io.kraluk.orderprocessor.test.db.outbox.OutboxDatabaseTestConfiguration;
import io.kraluk.orderprocessor.test.db.outbox.OutboxTestDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestClient;

@Import({OrderDatabaseTestConfiguration.class, OutboxDatabaseTestConfiguration.class})
public abstract class AcceptanceTest extends AwsIntegrationTest {

  @Autowired
  private RestClient testRestClient;

  @Autowired
  protected OrderTestDatabase orderTestDatabase;

  @Autowired
  protected OutboxTestDatabase outboxTestDatabase;

  public OrderUpdateExecutionsWebTestClient updateExecutionTestClient() {
    return new OrderUpdateExecutionsWebTestClient(testRestClient);
  }

}