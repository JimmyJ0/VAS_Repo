package de.leuphana.shop.connector.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.shop.structure.article.Article;

@RestController
@RequestMapping("/shop/article")
public class ShopKafkaController {
	
	private ShopKafkaProducer shopKafkaProducer;
	
	@Autowired
	public ShopKafkaController(ShopKafkaProducer articleKafkaProducer) {
		this.shopKafkaProducer = articleKafkaProducer;
	}
	
	@PostMapping("/saveArticle")
	public ResponseEntity<String> saveArticle(@RequestBody Article article) {
		shopKafkaProducer.sendArticle(article);
		return ResponseEntity.ok("article sent");
	}
	
	
	@PostMapping("/deleteArticle")
	public ResponseEntity<String> deleteArticle(@RequestBody Article article) {
		shopKafkaProducer.deleteArticle(article);
		return ResponseEntity.ok("article deleted");
	}

}
