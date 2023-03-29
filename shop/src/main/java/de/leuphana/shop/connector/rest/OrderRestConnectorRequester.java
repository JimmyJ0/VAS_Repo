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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.structure.billing.Invoice;
import de.leuphana.shop.structure.sales.Order;

@RestController
@RequestMapping("/shop/order")
public class OrderRestConnectorRequester {
	
	
	private ShopService shopService;
	
	@Autowired
	public OrderRestConnectorRequester(ShopService shopService) {
		this.shopService = shopService;
	}
	
	@GetMapping("/checkOutCart/{customerId}")
	public ResponseEntity<Order> createOrder(@PathVariable Integer customerId){
		HttpHeaders headers = new HttpHeaders();
		Order order = shopService.checkOutCart(customerId);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Order> requestEntity = new HttpEntity<>(order,headers);
		System.out.println(requestEntity.getBody());
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Order> response = restTemplate.exchange("http://localhost:9000/shop/order/createOrder", HttpMethod.POST, requestEntity, Order.class);
		Invoice invoice = shopService.createInvoice(response.getBody());
		printInvoice(invoice);
		if(response.getStatusCode() == HttpStatus.OK) return response;
		return null;
		
	}
	
	public ResponseEntity<Invoice> printInvoice(Invoice invoice){
		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
	}

}
