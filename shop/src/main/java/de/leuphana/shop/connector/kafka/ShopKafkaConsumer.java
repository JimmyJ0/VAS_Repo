package de.leuphana.shop.connector.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import de.leuphana.shop.behaviour.ShopService;

@Service
public class ShopKafkaConsumer {

	private ShopService shopService;

	@Autowired
	public ShopKafkaConsumer(ShopService shopService) {
		this.shopService = shopService;
	}
	
	 @KafkaListener(topics ="article_database", groupId = "article_group")
	 public void sendMessage(String message) {
		 shopService.receiveInfo(message);
	 }

}
