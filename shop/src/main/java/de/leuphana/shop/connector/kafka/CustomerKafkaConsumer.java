package de.leuphana.shop.connector.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import de.leuphana.shop.behaviour.ShopService;

@Service
public class CustomerKafkaConsumer {

	private ShopService shopService;

	public CustomerKafkaConsumer(ShopService shopService) {
		super();
		this.shopService = shopService;
	}

	@KafkaListener(topics ="customer_topic_create", groupId = "shop_group")
	public void sendCustomerMessage(String message) {
		shopService.receiveInfo(message);
	}
	
	@KafkaListener(topics ="customer_topic_delete", groupId = "shop_group")
	public void deleteCustomerMessage(String message) {
		shopService.receiveInfo(message);
	}

}
