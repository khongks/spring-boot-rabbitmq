package com.apress.spring;

import java.util.Date;

import com.apress.spring.rabbitmq.Producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class SpringBootRabbitmqApplication {

  private static final Logger log = LoggerFactory.getLogger(SpringBootRabbitmqApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRabbitmqApplication.class, args);
	}

	@Value("${myqueue}")
	String queue;

	@Value("${binding.shop.invoice}")
	String bindingShopInvoice;

	@Bean
	Queue queue(){
		return new Queue(queue,false);
	}

	@Autowired
	private Producer producer;

	// @Bean
	// CommandLineRunner sender(Producer producer){
	// 	return args -> {
	// 	//	producer.sendTo(queue, "Hello World");
	// 	producer.sendTo(directBinding, "Hello World");
	// 	};
	// }

	@Scheduled(fixedDelay = 2000L)
	public void sendMessages(){
		log.info("sendMessages> ...");
		//producer.sendTo(queue, "Hello World at " + new Date());
		producer.sendTo(queue, "Hello World at " + new Date());
	}
}
