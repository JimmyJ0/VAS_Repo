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
public class KafkaArticleController {
	
	private ArticleKafkaProducer articleKafkaProducer;
	
	@Autowired
	public KafkaArticleController(ArticleKafkaProducer articleKafkaProducer) {
		this.articleKafkaProducer = articleKafkaProducer;
	}
	
	@PostMapping("/saveArticle")
	public ResponseEntity<String> saveArticle(@RequestBody Article article) {
		articleKafkaProducer.sendArticle(article);
		return ResponseEntity.ok("article sent");
	}
	
	
	@PostMapping("/deleteArticle")
	public ResponseEntity<String> deleteArticle(@RequestBody Article article) {
		articleKafkaProducer.deleteArticle(article);
		return ResponseEntity.ok("article deleted");
	}

}
