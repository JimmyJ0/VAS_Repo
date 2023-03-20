package de.leuphana.shop.connector.rest;

import java.util.List;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;

@EnableDiscoveryClient
@RestController
@RequestMapping("/shop/shop")
public class ArticleRestConnectorRequester {
	// TODO: ExceptionHandling und Logging implementieren

	// Empfängt Artikel / Subtype und mapped diesen in konkerten Typ. Leitet dann an
	// entsprechende Methode weiter
	// Sendet einen Artikel an den article-ms.
	@PostMapping("/saveArticle")
	public boolean saveArticle(@RequestBody Article article) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON); // Teilt Server mit, dass body in JSON

		if (article instanceof CD) {
			HttpEntity<CD> payload = new HttpEntity<CD>((CD) article);
			ResponseEntity<String> response = restTemplate.exchange("http://localhost:9000/shop/article/cd",
					HttpMethod.POST, payload, String.class);
			if (response.getStatusCode() == HttpStatus.CREATED)
				return true;
		}

		else if (article instanceof Book) {
			HttpEntity<Book> payload = new HttpEntity<Book>((Book) article);
			ResponseEntity<String> response = restTemplate.exchange("http://localhost:9000/shop/article/book",
					HttpMethod.POST, payload, String.class);
			if (response.getStatusCode() == HttpStatus.CREATED)
				return true;
		}
		// Fehlgeschlagen
		return false;
	}

	// Holt alle Artikel aus der Datenbank
	@GetMapping("/getArticles")
	public List<Article> getArticles() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Article>> response = restTemplate.exchange(
				"http://localhost:9000/shop/article/getAllArticles", HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<Article>>() {
				});
		if (response.getStatusCode() == HttpStatus.OK)
			return response.getBody();
		return null;

	}

	@GetMapping("article/{id}")
	public Article getArticleById(@PathVariable String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Article> response = restTemplate.exchange(
				"http://localhost:9000/shop/article/getArticleById/{id}", HttpMethod.GET, requestEntity, Article.class,
				id);
		if (response.getStatusCode() == HttpStatus.OK)
			return response.getBody();
		return null;
	}

	@GetMapping("deleteArticle/{articleid}")
	public boolean deleteArticleById(@PathVariable String articleid) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> responseEntity = restTemplate.exchange(
				"http://localhost:9000/shop/article/deleteArticleById/{articleid}", HttpMethod.GET, requestEntity,
				String.class, articleid);
		if (responseEntity.getStatusCode() == HttpStatus.OK)
			return true;
		return false;
	}
	


}
