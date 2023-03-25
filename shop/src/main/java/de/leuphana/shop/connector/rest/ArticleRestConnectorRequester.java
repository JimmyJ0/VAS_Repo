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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.leuphana.shop.structure.article.Article;

@EnableDiscoveryClient
@RestController
@RequestMapping("/shop/article")
public class ArticleRestConnectorRequester {
	// TODO: ExceptionHandling und Logging implementieren

	// Holt alle Artikel aus der Datenbank
	@GetMapping("/getArticles")
	public List<Article> getArticles() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Article>> response = restTemplate.exchange(
				"http://api-gateway:9000/shop/article/getAllArticles", HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<Article>>() {
				});
		if (response.getStatusCode() == HttpStatus.OK)
			return response.getBody();
		return null;

	}

	@GetMapping("/article/{articleType}/{id}")
	public Article getArticleById(@PathVariable String articleType, @PathVariable String id ) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Article> response = restTemplate.exchange(
				"http://api-gateway:9000/shop/article/getArticleById/{articleType}/{id}", HttpMethod.GET, requestEntity, Article.class,
				articleType, id);
		if (response.getStatusCode() == HttpStatus.OK)
			return response.getBody();
		return null;
	}	
}
