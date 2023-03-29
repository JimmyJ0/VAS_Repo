package de.leuphana.shop.connector.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.structure.article.Article;

@RestController
@RequestMapping("/shop/article")
public class ArticleKafkaController {
	
	private ArticleKafkaProducer articleKafkaProducer;
	private ShopService shopService;
	
	@Autowired
	public ArticleKafkaController(ArticleKafkaProducer articleKafkaProducer, ShopService shopService) {
		this.articleKafkaProducer = articleKafkaProducer;
		this.shopService = shopService;
	}
	
	@PostMapping("/saveArticle")
	public ResponseEntity<String> saveArticle(@RequestBody Article article) {
		articleKafkaProducer.sendArticle(article);
		return ResponseEntity.ok("article sent");
	}
	
	@PostMapping("/updateArticle")
	public ResponseEntity<String> updateArticle(@RequestBody Article article) {
		articleKafkaProducer.sendArticle(article);
		return ResponseEntity.ok("article updated");
	}
	
	
	@PostMapping("/deleteArticle/{articleType}/{id}")
	public ResponseEntity<String> deleteArticle(@PathVariable String articleType, @PathVariable String id ) {
		if(shopService.getCatalog().get(id) != null) {
			articleKafkaProducer.deleteArticle(articleType, id);
			return ResponseEntity.ok("article deleted");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("catalog does not contain this article");
		

	}

}
