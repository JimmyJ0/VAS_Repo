package de.leuphana.shop.connector.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.structure.sales.Order;

@RestController
@RequestMapping("/shop/order")
public class ShopServiceRestConnector {
	
	
	private ShopService shopService;

	@Autowired
	public ShopServiceRestConnector(ShopService shopService) {
		this.shopService = shopService;
	}
	
	
	@GetMapping("/addArticle/{customerId}/{articleId}")
	public ResponseEntity<String> addArticleToCart(@PathVariable Integer customerId, @PathVariable String articleId) {
		shopService.addArticleToCart(customerId, articleId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/deleteArticle/{customerId}/{articleId}")
	public ResponseEntity<String> removeArticleFromCart(@PathVariable Integer customerId, @PathVariable String articleId){
		shopService.removeArticleFromCart(customerId, articleId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/changeArticleQuantity/{customerId}/{articleId}")
	public ResponseEntity<String> changeArticleQuantity(@PathVariable Integer customerId, @PathVariable String articleId){
		shopService.changeArticleQuantity(customerId, articleId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/checkOutCart/{customerId}")
	public ResponseEntity<String> checkOutCart(@PathVariable Integer customerId){
		Order order = shopService.checkOutCart(customerId);
		ResponseEntity<String> response = createOrder(order);
		if(response.getStatusCode() == HttpStatus.OK) return null;
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<String> createOrder(@RequestBody Order order){
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Order> requestEntity = new HttpEntity<>(order,headers);
		System.out.println(requestEntity.getBody());
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange("http://api-gateway:9000/shop/order/createOrder", HttpMethod.POST, requestEntity, String.class);
		
		if(response.getStatusCode() == HttpStatus.OK) return response;
		return null;
		
	}

}
