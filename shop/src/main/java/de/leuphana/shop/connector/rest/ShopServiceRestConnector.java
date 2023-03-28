package de.leuphana.shop.connector.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.structure.sales.Customer;

@RestController
@RequestMapping("/shop/")
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
		shopService.checkOutCart(customerId);
		return new ResponseEntity<String>(HttpStatus.OK);
		
	}

}
