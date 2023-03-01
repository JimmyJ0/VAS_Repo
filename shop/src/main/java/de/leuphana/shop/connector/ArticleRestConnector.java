package de.leuphana.shop.connector;


import org.springframework.http.HttpStatus;
// Das gleiche wie Controller?
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.leuphana.shop.component.structure.Article;


@RestController
@RequestMapping("/shop/shop")
public class ArticleRestConnector {

	// 1.
	// Controller empfängt Request vom Supplier mit Artikel-Daten als JSON
	// Mapped diese wieder in HTML und sendet dieses weiter an ArticleSpringDataConnectorRequester
	
	@PostMapping()
	public ResponseEntity<Article> saveArticle(@RequestBody Article article) {
		System.out.println("Sende Artikel weiter...");
		
		// RestTemplate bietet Methoden zum Senden von HTTP-Anfragen und Empfangen von HTTP-Antworten
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForLocation("http://localhost:9090//shop/article", article);
		
		// Object aus der RE rausziehen um dann in Catalog abzulegen?
		// Melded dem Clienten (Hier: Supplier, genauer dem Postman) den HttpStatusCode: CREATED 
		return new ResponseEntity<Article>(HttpStatus.CREATED);
		
	}
}
