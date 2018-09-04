package com.apress.spring.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
  private static final Logger log = LoggerFactory.getLogger(Consumer.class);

  @RabbitListener(queues="${myqueue}")
  public void handler(String message) {
    log.info("Consumer> " + message);
  }

  @RabbitListener(queues="${invoice.queue}")
  public void invoiceHandler(String message) {
    log.info("invoiceHandler> " + message);
  }

  @RabbitListener(queues="${delivery.queue}")
  public void deliveryHandler(String message) {
    log.info("deliveryHandler> " + message);
  }
}