package de.leuphana.shop.connector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;

@EnableDiscoveryClient
@RestController
@RequestMapping("/shop/shop")
public class ArticleRestConnectorRequester {

	private EurekaClient eurekaClient;

	@Autowired
	public void setEurekaClient(EurekaClient eurekaClient) {
		this.eurekaClient = eurekaClient;
	}

	// TODO: BADREQUESTS, ExceptionHandling und Logging implementieren

	// Empfängt Artikel / Subtype und mapped diesen in konkerten Typ. Leitet dann an
	// entsprechende Methode weiter
	@PostMapping()
	public ResponseEntity<Article> saveArticle(@RequestBody Article article) throws JsonProcessingException {
		Application app = eurekaClient.getApplication("article-service");
		String articleServiceUrl = "http://";
		if (app != null) {
			InstanceInfo articleServiceInstance = app.getInstances().get(0);
			String address = articleServiceInstance.getIPAddr() + ":" + articleServiceInstance.getPort();
			articleServiceUrl += address;

			if (article instanceof CD) {
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.postForLocation(articleServiceUrl + "//shop/article/cd", article);
				return new ResponseEntity<Article>(HttpStatus.CREATED);
			} else if (article instanceof Book) {
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.postForLocation(articleServiceUrl + "//shop/article/book", article);
				return new ResponseEntity<Article>(HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	// Holt alle Artikel aus der Datenbank
	@GetMapping()
	public ResponseEntity<List<Article>> getArticles() {
		Application app = eurekaClient.getApplication("article-service");
		String articleServiceUrl = "http://";
		if (app != null) {
			InstanceInfo articleServiceInstance = app.getInstances().get(0);
			String address = articleServiceInstance.getIPAddr() + ":" + articleServiceInstance.getPort();
			articleServiceUrl += address;

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON); // Server soll eine JSON zurückgeben
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();
			try {
				ResponseEntity<List<Article>> responseEntity = restTemplate.exchange(
						articleServiceUrl + "//shop/article/getAllArticles", HttpMethod.GET, requestEntity,
						new ParameterizedTypeReference<List<Article>>() {
						});
				List<Article> articles = responseEntity.getBody();
				return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		Application app = eurekaClient.getApplication("article-service");
		String articleServiceUrl = "http://";
		if (app != null) {
			InstanceInfo articleServiceInstance = app.getInstances().get(0);
			String address = articleServiceInstance.getIPAddr() + ":" + articleServiceInstance.getPort();
			articleServiceUrl += address;

			try {
				ResponseEntity<Article> responseEntity = restTemplate.exchange(
						articleServiceUrl + "//shop/article/getArticleById/{id}", HttpMethod.GET, requestEntity,
						Article.class, id);
				Article article = responseEntity.getBody();
				return new ResponseEntity<Article>(article, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@GetMapping("/{articleid}")
	public ResponseEntity<String> deleteArticleById(@PathVariable String articleid) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		Application app = eurekaClient.getApplication("article-service");
		String articleServiceUrl = "http://";
		if (app != null) {
			InstanceInfo articleServiceInstance = app.getInstances().get(0);
			String address = articleServiceInstance.getIPAddr() + ":" + articleServiceInstance.getPort();
			articleServiceUrl += address;

			try {
				ResponseEntity<String> responseEntity = restTemplate.exchange(
						articleServiceUrl + "//shop/article/deleteArticleById/{articleid}", HttpMethod.GET,
						requestEntity, String.class, articleid);
				if (responseEntity.getStatusCode().equals(HttpStatus.OK))
					return new ResponseEntity<String>("Article deleted!", HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;

	}
}
