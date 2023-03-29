package de.leuphana.shop.behaviour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.shop.structure.sales.Cart;

@RestController
@RequestMapping("/shop")
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
	
	@GetMapping("/decrementArticle/{customerId}/{articleId}")
	public ResponseEntity<String> changeArticleQuantity(@PathVariable Integer customerId, @PathVariable String articleId){
		shopService.changeArticleQuantity(customerId, articleId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/getCartFromCustomer/{customerId}")
	public ResponseEntity<String> getCartFromCustomer(@PathVariable Integer customerId){
		Cart cart = shopService.getCustomers().get(customerId).getCart();
		if(cart != null) {
			StringBuilder sb = new StringBuilder();
			cart.getCartItems().forEach(cartItem -> sb.append("Article Id: " + cartItem.getArticleId() + " Quantity: " + cartItem.getQuantity() + " Price: " + cartItem.getPrice() + "\n"));
			return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
		}
		return new ResponseEntity<String>("There are no items in the cart!", HttpStatus.BAD_REQUEST);
	}
}
