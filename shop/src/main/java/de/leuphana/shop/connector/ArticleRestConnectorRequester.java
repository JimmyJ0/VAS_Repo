package de.leuphana.shop.connector;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
// Das gleiche wie Controller?
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.leuphana.shop.component.structure.article.Article;
import de.leuphana.shop.component.structure.article.Book;
import de.leuphana.shop.component.structure.article.CD;

@RestController
@RequestMapping("/shop/shop")
public class ArticleRestConnector {
	
	//TODO: BADREQUESTS, ExceptionHandling und Logging implementieren
	//TODO: Letzten beiden CRUD-Operations implementieren: GetArticleByID, DeleteArticle, UpdateArticle

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

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	// Holt alle Artikel aus der Datenbank
	@GetMapping()
	public ResponseEntity<List<Article>> getArticles() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON); // Server soll eine JSON zurückgeben
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<List<Article>> responseEntity = restTemplate.exchange("http://localhost:9090//shop/article/getAllArticles", HttpMethod.GET,requestEntity, new ParameterizedTypeReference<List<Article>>() {});
			List<Article> articles = responseEntity.getBody();
			return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable String id) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	        ResponseEntity<Article> responseEntity = restTemplate.exchange(
	            "http://localhost:9090//shop/article/getArticleById/{id}", HttpMethod.GET,
	            requestEntity, Article.class, id);
	        Article article = responseEntity.getBody();
	        return new ResponseEntity<Article>(article, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
