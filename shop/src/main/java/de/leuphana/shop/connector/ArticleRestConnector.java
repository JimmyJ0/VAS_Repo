package de.leuphana.shop.connector;

import org.springframework.http.HttpStatus;
// Das gleiche wie Controller?
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

<<<<<<< HEAD
import com.fasterxml.jackson.core.JsonProcessingException;
=======
import de.leuphana.shop.component.structure.article.Article;
>>>>>>> branch 'master' of https://github.com/JimmyJ0/VAS_Repo.git

import de.leuphana.shop.component.structure.Article;
import de.leuphana.shop.component.structure.Book;
import de.leuphana.shop.component.structure.CD;

@RestController
@RequestMapping("/shop/shop")
public class ArticleRestConnector {

	// Empfängt Artikel / Subtype und mapped diesen in konkerten Typ. Leitet dann an
	// entsprechende Methode weiter
	@PostMapping()
	public ResponseEntity<Article> saveArticle(@RequestBody Article article) throws JsonProcessingException {

		if (article instanceof CD) {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForLocation("http://localhost:9090//shop/article/cd", article);
			return new ResponseEntity<Article>(HttpStatus.CREATED);
		} else if (article instanceof Book) {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForLocation("http://localhost:9090//shop/article/book", article);
			return new ResponseEntity<Article>(HttpStatus.CREATED);
		}

		return null;

	}
}
