package de.leuphana.shop.connector.controller.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.shop.structure.article.Article;

@RestController
@RequestMapping("/shop/shop/article")
public class KafkaArticleController {
	
	private ArticleKafkaProducer articleKafkaProducer;
	
	@Autowired
	public KafkaArticleController(ArticleKafkaProducer articleKafkaProducer) {
		this.articleKafkaProducer = articleKafkaProducer;
	}
	
	@PostMapping("/saveArticle2")
	public ResponseEntity<String> saveArticle2(@RequestBody Article article) {
		articleKafkaProducer.sendArticle(article);
		return ResponseEntity.ok("JSON SEND");
		
		
	}

}
