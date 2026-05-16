package com.app.vdc.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;

@Configuration
public class KafkaConfig {

	// @Bean
	// public KafkaTemplate<String, String> kafkaTemplate() {
	// final var configs = new HashMap<String, Object>();
	// configs.put("bootstrap.servers", "localhost:9092");
	// configs.put("key.serializer",
	// "org.apache.kafka.common.serialization.StringSerializer");
	// configs.put("value.serializer",
	// "org.apache.kafka.common.serialization.StringSerializer");
	// return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(configs));
	// }

}
